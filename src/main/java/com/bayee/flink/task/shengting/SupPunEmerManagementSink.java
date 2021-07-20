package com.bayee.flink.task.shengting;

import com.alibaba.fastjson.JSONObject;
import com.bayee.flink.sink.HbaseRichSinkFunction;
import com.bayee.hbase.htable.impl.shengting.SupPunEmerManagementHTable;
import com.bayee.utils.HbaseUtils;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.hadoop.hbase.client.Put;

import java.util.Properties;

/**
 * @ClassName SupPunEmerManagementSink
 * @Description: 样例
 * @Author Lyb
 * @Date 2021/3/11
 **/
public class SupPunEmerManagementSink {

    private static final String TOPIC_NAME="sup-pun-emer-management";
    private static final String TABLE_NAME="hb_sup_pun_emer_management";

    public static void main(String[] args) throws Exception {

        String className = SupPunEmerManagementSink.class.getName();
        System.out.println("use command as: ");
        System.out.println("./bin/flink run -m yarn-cluster -ynm <TaskName> -yjm 1024 -ytm 1024 -c "+ className +" /path/jar");
        System.out.println("******************************************************************************************");
        System.out.println("<TaskName> 启动命令中定义任务名称");
        System.out.println("******************************************************************************************");

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        FsStateBackend fsStateBackend = new FsStateBackend("hdfs://manager1.ambari.com:8020/flink/checkpoints");
        env.setStateBackend(fsStateBackend);
        env.enableCheckpointing(5000);
        env.setParallelism(3);
        env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime);

        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "kafka.ambari.com:9095");
        props.setProperty("auto.offset.reset","earliest");

        props.setProperty("group.id", "sup-pun-emer-management-group");

        FlinkKafkaConsumer<String> consumer =
                new FlinkKafkaConsumer<>(TOPIC_NAME, new SimpleStringSchema(), props);

        DataStreamSource<String> mainStreaming = env.addSource(consumer);

        mainStreaming
        .map(new MapFunction<String, SupPunEmerManagementHTable>() {
            @Override
            public SupPunEmerManagementHTable map(String s) throws Exception {
                return JSONObject.parseObject(s, SupPunEmerManagementHTable.class);
            }
        }).map(new MapFunction<SupPunEmerManagementHTable, Put>() {
            @Override
            public Put map(SupPunEmerManagementHTable put) throws Exception {
                return HbaseUtils.getPut(put);
            }
        })
        .addSink(new HbaseRichSinkFunction(TABLE_NAME));

        env.execute("SupPunEmerManagementSink");
    }
}
