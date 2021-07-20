package com.bayee.flink.sink;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.runtime.util.ExecutorThreadFactory;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.TableNotFoundException;
import org.apache.hadoop.hbase.client.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName HbaseRichSink
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/3/18
 **/
public class HbaseRichSinkFunction extends RichSinkFunction<Put> {

    private final static Logger LOG = Logger.getLogger(HbaseRichSinkFunction.class);
    private Connection conn;
    private BufferedMutator mutator;
    private AtomicLong numPendingRequests;
    private long bufferFlushMaxSizeInBytes = 1024*1024;
    private long bufferFlushIntervalMillis = 5000;
    private long bufferFlushMaxMutations = 1000;
    private ScheduledExecutorService executor;
    private ScheduledFuture scheduledFuture;
    private String tableName;

    public HbaseRichSinkFunction(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        LOG.info("start open ...");
        org.apache.hadoop.conf.Configuration configuration = HBaseConfiguration.create();
        configuration.set(HConstants.ZOOKEEPER_QUORUM, "manager1.ambari.com:2181");
        configuration.set(HConstants.ZOOKEEPER_CLIENT_PORT, "2181");
        configuration.setInt(HConstants.HBASE_CLIENT_OPERATION_TIMEOUT, 30000);
        configuration.setInt(HConstants.HBASE_CLIENT_SCANNER_TIMEOUT_PERIOD, 30000);

        try {
            // 初始化mutationConverter
            // 记录尚未flush到HBase的数据条数
            this.numPendingRequests = new AtomicLong(0);

            // 创建HBase连接
            if (conn == null) {
                this.conn = ConnectionFactory.createConnection(configuration);
            }
            // create a parameter instance, set the table name and custom listener reference.
            // 创建BufferedMutator构建参数
            // 包含写入HBase的目标表名称和ExceptionListener
            BufferedMutatorParams params =
                    new BufferedMutatorParams(TableName.valueOf(tableName));
            // 设置BufferedMutator的写缓存字节数
            if (bufferFlushMaxSizeInBytes > 0) {
                params.writeBufferSize(bufferFlushMaxSizeInBytes);
            }
            // 根据前面的参数，创建一个BufferedMutator
            this.mutator = conn.getBufferedMutator(params);

            // 如果flush间隔大于0ms，且最大待flush数据条数不等于1时
            // 创建一个定时flush BufferedMutator的任务
            if (bufferFlushIntervalMillis > 0 && bufferFlushMaxMutations != 1) {
                this.executor =
                        Executors.newScheduledThreadPool(
                                1, new ExecutorThreadFactory("hbase-upsert-sink-flusher"));
                this.scheduledFuture =
                        this.executor.scheduleWithFixedDelay(
                                () -> {
                                    try {
                                        flush();
                                    } catch (Exception e) {

                                    }
                                },
                                bufferFlushIntervalMillis,
                                bufferFlushIntervalMillis,
                                TimeUnit.MILLISECONDS);
            }
        } catch (TableNotFoundException tnfe) {
            LOG.error("The table " + tableName + " not found ", tnfe);
            throw new RuntimeException("HBase table '" + tableName + "' not found.", tnfe);
        } catch (IOException ioe) {
            LOG.error("Exception while creating connection to HBase.", ioe);
            throw new RuntimeException("Cannot create connection to HBase.", ioe);
        }
        LOG.info("end open.");
    }

    @Override
    public void close() throws Exception {
        super.close();
        if (conn != null) { conn.close();}
    }

    @Override
    public void invoke(Put put, Context context) throws Exception {
        mutator.mutate(put);
        // flush when the buffer number of mutations greater than the configured max size.
        // numPendingRequests记录了未发送的写入请求个数
        // 如果numPendingRequests大于bufferFlushMaxMutations，强制flush到HBase
        if (bufferFlushMaxMutations > 0
                && numPendingRequests.incrementAndGet() >= bufferFlushMaxMutations) {
            flush();
        }
    }

    private void flush() throws IOException {
        // BufferedMutator is thread-safe
        // 调用BufferedMutator的flush方法
        mutator.flush();
        // 重置pendingRequest计数器为0
        numPendingRequests.set(0);
    }
}
