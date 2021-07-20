package com.bayee.hbase.htable.impl.shengting;

import com.alibaba.fastjson.JSONObject;
import com.bayee.hbase.htable.TutorialData;
import org.geotools.data.Query;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.filter.text.cql2.CQLException;
import org.geotools.filter.text.ecql.ECQL;
import org.geotools.util.factory.Hints;
import org.locationtech.geomesa.utils.interop.SimpleFeatureTypes;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author antuo
 * @since 2021/6/30 16:00
 */
public class BoatShipData implements TutorialData {

    private static final Logger logger = LoggerFactory.getLogger(BoatShipData.class);

    private SimpleFeatureType sft = null;
    private List<SimpleFeature> features = null;
    private List<Query> queries = null;
    private Filter subsetFilter = null;

    @Override
    public String getTypeName() {
        return "boat";
    }

    @Override
    public SimpleFeatureType getSimpleFeatureType() {
        if (sft == null) {
            StringBuilder attributes = new StringBuilder();
            attributes.append("mmsi:String,");
            attributes.append("boat_name:String,");
            attributes.append("type:String,");
            attributes.append("subType:String,");
            attributes.append("is_has_mmsi:Integer,");
            attributes.append("source:String,");
            attributes.append("areaid:String,");
            attributes.append("boat_speed:Float,");
            attributes.append("is_registration:Integer,");
            attributes.append("update_time:Date,");
            attributes.append("*location_point:Point:srid=4326,");
            attributes.append("registration:String,");
            attributes.append("longitude:Double,");
            attributes.append("latitude:Double");

            // create the simple-feature type - use the GeoMesa 'SimpleFeatureTypes' class for best compatibility
            // may also use geotools DataUtilities or SimpleFeatureTypeBuilder, but some features may not work

            sft = SimpleFeatureTypes.createType(getTypeName(), attributes.toString());

            // use the user-data (hints) to specify which date field to use for primary indexing
            // if not specified, the first date attribute (if any) will be used
            // could also use ':default=true' in the attribute specification string
            sft.getUserData().put(SimpleFeatureTypes.DEFAULT_DATE_KEY, "update_time");
        }
        return sft;
    }

    @Override
    public List<SimpleFeature> getTestData(List<String> jsons) {
        if (features == null) {
            List<SimpleFeature> features = new ArrayList<>();
            // date parser corresponding to the CSV format
            //DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.US);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // use a geotools SimpleFeatureBuilder to create our features
            SimpleFeatureBuilder builder = new SimpleFeatureBuilder(getSimpleFeatureType());
            for (String json : jsons) {
                JSONObject jsonObject = JSONObject.parseObject(json);
                for (String s : jsonObject.keySet()) {
                    if ("update_time".equals(s)) {
                        builder.set(s, Date.from(LocalDateTime.parse(sdf.format(jsonObject.getDate(s)), dateFormat).toInstant(ZoneOffset.ofHours(8))));
                    } else {
                        builder.set(s,jsonObject.get(s));
                    }
                }
                builder.featureUserData(Hints.USE_PROVIDED_FID, Boolean.TRUE);
                SimpleFeature feature = builder.buildFeature(jsonObject.getString("mmsi"));

                features.add(feature);
            }
            this.features = Collections.unmodifiableList(features);
        }
        return features;
    }

    @Override
    public List<Query> getTestQueries(String ecql) {
        if (queries == null) {
            try {
                List<Query> queries = new ArrayList<>();


                //ecql = "bbox(location_point,115,30,130,55)";

                queries.add(new Query(getTypeName(), ECQL.toFilter(ecql)));

                this.queries = Collections.unmodifiableList(queries);
            } catch (CQLException e) {
                throw new RuntimeException("Error creating filter:", e);
            }
        }
        return queries;
    }

    @Override
    public Filter getSubsetFilter() {
        if (subsetFilter == null) {
            // Get a FilterFactory2 to build up our query
            FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();

            // most of the data is from 2018-01-01
            ZonedDateTime dateTime = ZonedDateTime.of(2018, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
            Date start = Date.from(dateTime.minusDays(1).toInstant());
            Date end = Date.from(dateTime.plusDays(1).toInstant());

            // note: BETWEEN is inclusive, while DURING is exclusive
            Filter dateFilter = ff.between(ff.property("dtg"), ff.literal(start), ff.literal(end));

            // bounding box over small portion of the eastern United States
            Filter spatialFilter = ff.bbox("geom",-83,33,-80,35,"EPSG:4326");

            // Now we can combine our filters using a boolean AND operator
            subsetFilter = ff.and(dateFilter, spatialFilter);

            // note the equivalent using ECQL would be:
            // ECQL.toFilter("bbox(geom,-83,33,-80,35) AND dtg between '2017-12-31T00:00:00.000Z' and '2018-01-02T00:00:00.000Z'");
        }
        return subsetFilter;
    }

    public static void main(String[] args) throws ParseException {
        GeometryFactory geometryFactory = new GeometryFactory();
        WKTReader reader = new WKTReader(geometryFactory);

        String wktZGPoly = "POLYGON ((94.8164083957672 3.8499999999999943, 135.08416666666668 3.8499999999999943, 135.09972222222223 53.55277777777778, 92.9882844686508 53.351862884566486, 93.12891805171965 46.19504247998822, 93.83204305171965 32.57644331850494, 94.8164083957672 3.8499999999999943))";
        String wktPoint = "POINT (120 10)";

        Geometry point = reader.read(wktPoint);
        Geometry poly = reader.read(wktZGPoly);
        System.out.println(point.within(poly));
    }
}
