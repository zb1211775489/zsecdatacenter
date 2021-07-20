package com.bayee.flink.sink;


import com.bayee.hbase.htable.TutorialData;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.geotools.data.*;
import org.geotools.data.DataAccessFactory.Param;
import org.geotools.data.simple.SimpleFeatureStore;
import org.geotools.filter.identity.FeatureIdImpl;
import org.geotools.filter.text.ecql.ECQL;
import org.geotools.util.factory.Hints;
import org.locationtech.geomesa.hbase.data.HBaseDataStoreFactory;
import org.locationtech.geomesa.index.geotools.GeoMesaDataStore;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.opengis.filter.sort.SortBy;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * dd
 *
 * @author antuo
 * @since 2021/6/29 16:21
 */
public class GeomesaHbaseClient {

    private final Map<String, String> params;
    private final TutorialData data;
    private final boolean cleanup;
    private final boolean readOnly;

    public GeomesaHbaseClient(String[] args, TutorialData data) throws ParseException {
        this(args, data, false);
    }

    public GeomesaHbaseClient(String[] args, TutorialData data, boolean readOnly) throws ParseException {
        // parse the data store parameters from the command line
        Options options = createOptions(new HBaseDataStoreFactory().getParametersInfo());
        CommandLine command = CommandLineDataStore.parseArgs(getClass(), options, args);
        this.params = CommandLineDataStore.getDataStoreParams(command, options);
        this.cleanup = command.hasOption("cleanup");
        this.data = data;
        this.readOnly = readOnly;
        initializeFromOptions(command);
    }

    public List<SimpleFeature> getTestFeatures(TutorialData data, List<String> jsons) {
        List<SimpleFeature> features = data.getTestData(jsons);
        return features;
    }


    public void writeFeatures(DataStore datastore, SimpleFeatureType sft, List<SimpleFeature> features) throws IOException {
        if (features.size() > 0) {
            // use try-with-resources to ensure the writer is closed
            try (FeatureWriter<SimpleFeatureType, SimpleFeature> writer =
                         datastore.getFeatureWriterAppend(sft.getTypeName(), Transaction.AUTO_COMMIT)) {
                for (SimpleFeature feature : features) {
                    // using a geotools writer, you have to get a feature, modify it, then commit it
                    // appending writers will always return 'false' for haveNext, so we don't need to bother checking
                    SimpleFeature toWrite = writer.next();

                    // copy attributes
                    toWrite.setAttributes(feature.getAttributes());

                    // if you want to set the feature ID, you have to cast to an implementation class
                    // and add the USE_PROVIDED_FID hint to the user data
                    ((FeatureIdImpl) toWrite.getIdentifier()).setID(feature.getID());
                    toWrite.getUserData().put(Hints.USE_PROVIDED_FID, Boolean.TRUE);

                    // alternatively, you can use the PROVIDED_FID hint directly
                    // toWrite.getUserData().put(Hints.PROVIDED_FID, feature.getID());

                    // if no feature ID is set, a UUID will be generated for you

                    // make sure to copy the user data, if there is any
                    toWrite.getUserData().putAll(feature.getUserData());

                    // write the feature
                    writer.write();
                }
            }
        }
    }

    public void queryFeatures(DataStore datastore, List<Query> queries) throws IOException {
        for (Query query : queries) {
            System.out.println("Running query " + ECQL.toCQL(query.getFilter()));
            if (query.getPropertyNames() != null) {
                System.out.println("Returning attributes " + Arrays.asList(query.getPropertyNames()));
            }
            if (query.getSortBy() != null) {
                SortBy sort = query.getSortBy()[0];
                System.out.println("Sorting by " + sort.getPropertyName() + " " + sort.getSortOrder());
            }
            // submit the query, and get back an iterator over matching features
            // use try-with-resources to ensure the reader is closed
            try (FeatureReader<SimpleFeatureType, SimpleFeature> reader =
                         datastore.getFeatureReader(query, Transaction.AUTO_COMMIT)) {
                // loop through all results, only print out the first 10
                int n = 0;
                while (reader.hasNext()) {
                    SimpleFeature feature = reader.next();
                    if (n++ < 40) {
                        // use geotools data utilities to get a printable string
                        System.out.println(String.format("%02d", n) + " " + DataUtilities.encodeFeature(feature));
                    } else if (n == 40) {
                        System.out.println("...");
                    }
                }
                System.out.println();
                System.out.println("Returned " + n + " total features");
                System.out.println();
            }
        }
    }

    public void cleanup(DataStore datastore, String typeName) {
        if (datastore != null) {
            try {
                if (cleanup) {
                    if (datastore instanceof GeoMesaDataStore) {
                        ((GeoMesaDataStore) datastore).delete();
                    } else {
                        ((SimpleFeatureStore) datastore.getFeatureSource(typeName)).removeFeatures(Filter.INCLUDE);
                        datastore.removeSchema(typeName);
                    }
                }
            } catch (Exception e) {
                System.err.println("Exception cleaning up test data: " + e.toString());
            } finally {
                // make sure that we dispose of the datastore when we're done with it
                datastore.dispose();
            }
        }
    }

    public DataStore createDataStore() throws IOException {
        System.out.println("Loading datastore");

        // use geotools service loading to get a datastore instance
        DataStore datastore = DataStoreFinder.getDataStore(params);
        if (datastore == null) {
            throw new RuntimeException("Could not create data store with provided parameters");
        }
        return datastore;
    }

    public Options createOptions(Param[] parameters) {
        // parse the data store parameters from the command line
        Options options = CommandLineDataStore.createOptions(parameters);
        if (!readOnly) {
            options.addOption(Option.builder().longOpt("cleanup").desc("Delete tables after running").build());
        }
        return options;
    }

    public void initializeFromOptions(CommandLine command) {
    }

    public void ensureSchema(DataStore datastore, TutorialData data) throws IOException {
        SimpleFeatureType sft = datastore.getSchema(data.getTypeName());
        if (sft == null) {
            throw new IllegalStateException("Schema '" + data.getTypeName() + "' does not exist. " +
                    "Please run the associated QuickStart to generate the test data.");
        }
    }

    public SimpleFeatureType getSimpleFeatureType(TutorialData data) {
        return data.getSimpleFeatureType();
    }


    public void createSchema(DataStore datastore, SimpleFeatureType sft) throws IOException {

        // we only need to do the once - however, calling it repeatedly is a no-op
        sft.getUserData().put("override.reserved.words",true);
        datastore.createSchema(sft);

    }

}
