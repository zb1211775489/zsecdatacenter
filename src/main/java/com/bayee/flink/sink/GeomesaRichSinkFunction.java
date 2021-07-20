package com.bayee.flink.sink;


import com.bayee.hbase.htable.TutorialData;
import com.bayee.hbase.htable.impl.shengting.BoatShipData;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.runtime.util.ExecutorThreadFactory;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.geotools.data.DataStore;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import java.util.ArrayList;
import java.util.List;
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
public class GeomesaRichSinkFunction extends RichSinkFunction<String> {

    private GeomesaHbaseClient geomesaHbaseClient;
    private DataStore dataStore;
    private TutorialData data;
    private String[] args;
    private List<String> jsons = new ArrayList<>();
    private AtomicLong numPendingRequests;
    private long bufferFlushMaxMutations = 1000;
    private long bufferFlushIntervalMillis = 5000;
    private ScheduledExecutorService executor;
    private ScheduledFuture scheduledFuture;

    @Override
    public void open(Configuration parameters) throws Exception {
        args = new String[]{"--hbase.catalog", "zs:yhh", "--hbase.zookeepers", "manager1:2181"};
        data = new BoatShipData();
        geomesaHbaseClient = new GeomesaHbaseClient(args, data, false);
        this.numPendingRequests = new AtomicLong(0);

        if (bufferFlushIntervalMillis > 0 && bufferFlushMaxMutations != 1) {

            this.executor =
                    Executors.newScheduledThreadPool(
                            1, new ExecutorThreadFactory("hbase-upsert-sink-flusher"));
            this.scheduledFuture =
                    this.executor.scheduleWithFixedDelay(
                            () -> {
                                try {
                                    if(numPendingRequests.get() > 0) {
                                        dataStore = geomesaHbaseClient.createDataStore();
                                        try {
                                            SimpleFeatureType sft = geomesaHbaseClient.getSimpleFeatureType(data);
                                            geomesaHbaseClient.createSchema(dataStore, sft);
                                            List<SimpleFeature> features = geomesaHbaseClient.getTestFeatures(data, jsons);
                                            geomesaHbaseClient.writeFeatures(dataStore, sft, features);
                                        } catch (Exception e) {
                                            throw new RuntimeException("Error running quickstart:", e);
                                        } finally {
                                            geomesaHbaseClient.cleanup(dataStore, data.getTypeName());
                                        }
                                        System.out.println("Done");
                                        numPendingRequests.set(0);
                                        jsons.clear();
                                    }
                                } catch (Exception e) {

                                }
                            },
                            bufferFlushIntervalMillis,
                            bufferFlushIntervalMillis,
                            TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public void invoke(String json, Context context) throws Exception {
        jsons.add(json);
        if (numPendingRequests.incrementAndGet() >= bufferFlushMaxMutations) {
//            data = new BoatShipData();
//            geomesaHbaseClient = new GeomesaHbaseClient(args,data,false);
            dataStore = geomesaHbaseClient.createDataStore();
            try {
                SimpleFeatureType sft = geomesaHbaseClient.getSimpleFeatureType(data);
                geomesaHbaseClient.createSchema(dataStore, sft);
                List<SimpleFeature> features = geomesaHbaseClient.getTestFeatures(data,jsons);
                geomesaHbaseClient.writeFeatures(dataStore, sft, features);
            } catch (Exception e) {
                throw new RuntimeException("Error running quickstart:", e);
            } finally {
                geomesaHbaseClient.cleanup(dataStore, data.getTypeName());
            }
            numPendingRequests.set(0);
            jsons.clear();
        }
    }
}
