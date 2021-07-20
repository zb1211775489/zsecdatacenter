package com.bayee.flink.task.shengting;

import com.alibaba.fastjson.JSONObject;
import com.bayee.flink.sink.GeomesaRichSinkFunction;
import com.bayee.hbase.htable.impl.shengting.ReaNatFishingBoatRealGeomesa;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import java.util.Properties;

/**
 * @ClassName TestFlinkSink
 * @Description: 样例
 * @Author Lyb
 * @Date 2021/3/11
 **/
public class ReaNatFishingBoatGeomesaSink {

    private static final String TOPIC_NAME="rea-fishing-boat";

    public static void main(String[] args) throws Exception {

        String className = ReaNatFishingBoatGeomesaSink.class.getName();
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

        props.setProperty("group.id", "rea-nat-fishing-boat-geomesa-group");

        FlinkKafkaConsumer<String> consumer =
                new FlinkKafkaConsumer<>(TOPIC_NAME, new SimpleStringSchema(), props);

        DataStreamSource<String> mainStreaming = env.addSource(consumer);

        mainStreaming
                .map(new MapFunction<String, String>() {
                    @Override
                    public String map(String s) throws Exception {
                        JSONObject jsonObject = JSONObject.parseObject(s);
                        double lon  = Double.valueOf(jsonObject.getString("longitude"));
                        double lat  = Double.valueOf(jsonObject.getString("latitude"));
                        String areaId = getAreaId(lon,lat);
                        jsonObject.fluentPut("areaid",areaId);
                        String point = "POINT ("+lon+" "+lat+")";
                        jsonObject.fluentPut("location_point",point);
                        ReaNatFishingBoatRealGeomesa reaNatFishingBoatRealHTable = JSONObject.parseObject(jsonObject.toJSONString(), ReaNatFishingBoatRealGeomesa.class);
                        return JSONObject.toJSONString(reaNatFishingBoatRealHTable);
                    }
                }).addSink(new GeomesaRichSinkFunction());

        env.execute("ReaNatFishingBoatGeomesaSink");
    }

    public static String getAreaId(Double lon,Double lat) throws ParseException {
        GeometryFactory geometryFactory = new GeometryFactory();
        WKTReader reader = new WKTReader(geometryFactory);

        String wktZSPoly = "POLYGON ((121.55 31, 123.15 31, 123.15 29.40, 121.55 29.40, 121.55 31))";
        String wktZJPoly = "POLYGON ((119.64166261022912 30.968660279949773, 119.46203613281249 29.696642167833502, 121.54064941406249 29.191491761578533, 122.19790652766822 29.51634936889691, 122.04286194685848 29.694852766305914, 121.92544560693202 29.720736344006397, 121.94219974055886 29.772483395444382, 121.99713138118386 29.76676161308953, 122.14613343961535 29.879827012751008, 122.18019103165717 29.926493189927527, 122.03015900496392 29.92762387000876, 121.9601212008856 29.97902596299481, 121.86865996895355 29.95332824645277, 121.83652496756983 29.96189489333608, 121.75103778205812 30.00489541599474, 121.55548099055886 30.270928381697104, 121.56866455078125 30.921547608766787, 120.24591067805885 30.927202382853565, 119.64166261022912 30.968660279949773))";
        String wktZJPoly2 = "POLYGON ((120.27062998339532 27.466363062420925, 120.82544019073246 27.068908976043403, 122.26135247200723 27.63000705259462, 122.58654785156247 29.452034741576426, 122.19543470442294 29.517066430718828, 121.90539547428487 29.36015443389371, 121.554931640625 29.192451033125977, 121.36157219856977 28.77728892245011, 120.27062998339532 27.466363062420925))";
        String wktZGPoly = "POLYGON ((94.8164083957672 3.8499999999999943, 135.08416666666668 3.8499999999999943, 135.09972222222223 53.55277777777778, 92.9882844686508 53.351862884566486, 93.12891805171965 46.19504247998822, 93.83204305171965 32.57644331850494, 94.8164083957672 3.8499999999999943))";
        String wktDHPoly = "POLYGON ((121.84840393904598 30.26820042999796, 121.96650697058065 30.262507077423777, 122.03791809920223 30.261083702178354, 122.06998443393968 30.198374945358992, 122.08509063511156 30.169172376074073, 122.13943863334134 30.145275630308547, 122.16529082832857 30.12167005292072, 122.27467345353216 30.12054155107343, 122.20951080322267 29.96786862047469, 122.18112766749252 29.927587603167098, 122.02957535209134 29.927951159321907, 121.95819855993612 29.978788060548666, 121.86711502075194 29.953774440759062, 121.83566666801926 29.962579001915785, 121.75934600830077 30.000911404681034, 121.61549377022311 30.201282823013045, 121.84840393904598 30.26820042999796))";
        String wktDSPoly = "POLYGON ((121.55726623954251 30.53919923617589, 121.98126984533155 30.54434427444366, 122.3433380126953 30.61675927896586, 122.40980528946967 30.603048956062253, 122.78443907853216 30.48619581259291, 123.05850505828859 30.463189142649654, 122.98146128654481 30.264970164713972, 122.62324905185962 30.24177699098759, 122.57840466499327 30.15399082074373, 122.43889868265978 30.13611707410557, 122.27522278460674 30.120541583698895, 122.16553116473368 30.121640353188624, 122.14026260271201 30.14491935802033, 122.08509492874143 30.16919093105213, 122.03814125008648 30.261157838285555, 121.96064308148276 30.264031944508332, 121.84838891068648 30.268194866751998, 121.61549484765783 30.201283790053566, 121.55960092227905 30.27069117245935, 121.55726623954251 30.53919923617589))";
        String wktPTPoly = "POLYGON ((122.19502259558067 29.521488012475075, 122.57321349450578 29.464908736476488, 122.66302349963233 29.63752204662771, 122.9453873461204 30.167974864473976, 122.97919844089434 30.263683022191643, 122.62348938826473 30.241628691273675, 122.57837677001953 30.15397411162249, 122.43878177180885 30.136101256977696, 122.27468419088109 30.120543446976598, 122.20923615130597 29.96570205658648, 122.14558410644531 29.87935070009027, 121.99465941544622 29.762708395227435, 121.94343567523173 29.760681742996894, 121.92881014267914 29.720795968585662, 122.0419693056028 29.695986037617644, 122.19502259558067 29.521488012475075))";
        String wktSSPoly = "POLYGON ((121.55735919936842 30.53916089494963, 121.56127370230409 30.685066557343063, 121.87432526567805 30.71503150263146, 122.16757835327172 30.867816791310716, 122.8008560605096 31.003865345776802, 123.16425592626638 30.729410647214493, 123.05856004357338 30.46316371126636, 122.78436988592149 30.486160794915747, 122.40919168769383 30.60325787066833, 122.34328047410757 30.61677189024728, 121.98121968411891 30.544334052824382, 121.55735919936842 30.53916089494963))";
        Coordinate coord = new Coordinate(lon,lat);
        Point point = geometryFactory.createPoint(coord);
        Geometry zsPoly = reader.read(wktZSPoly);
        Geometry zjPoly = reader.read(wktZJPoly);
        Geometry zjPoly2 = reader.read(wktZJPoly2);
        Geometry zgPoly = reader.read(wktZGPoly);
        Geometry dhPoly = reader.read(wktDHPoly);
        Geometry dsPoly = reader.read(wktDSPoly);
        Geometry ptPoly = reader.read(wktPTPoly);
        Geometry ssPoly = reader.read(wktSSPoly);

        if(dsPoly.contains(point)) {
            return "6";
        } else if(ssPoly.contains(point)) {
            return "8";
        } else if(dhPoly.contains(point)) {
            return "5";
        } else if(ptPoly.contains(point)) {
            return "7";
        } else if(zsPoly.contains(point)) {
            return "1";
        } else if(zjPoly.contains(point)) {
            return "2";
        } else if(zjPoly2.contains(point)) {
            return "2";
        } else if(zgPoly.contains(point)) {
            return "3";
        } else {
            return "4";
        }
    }
}
