package com.poc.mongo.pocmongoservice.Repository.MongoDB;

import com.mongodb.BulkWriteException;
import com.mongodb.MongoClient;
import com.mongodb.BulkWriteError;
import com.mongodb.MongoClientSettings;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.UpdateOneModel;
import com.mongodb.client.model.UpdateOptions;
import com.poc.mongo.pocmongoservice.Models.DataModel;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MongoDbRepository {

    private MongoClient Client;
    private MongoDatabase Database;
    private MongoCollection<DataModel> Collection;

    public boolean collectionExists(final String collectionName) {
        MongoIterable<String> collectionNames = this.Database.listCollectionNames();
        for (final String name : collectionNames) {
            if (name.equalsIgnoreCase(collectionName)) {
                return true;
            }
        }
        return false;
    }

    public MongoDbRepository() {

        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        this.Client = new MongoClient("localhost");
        this.Database = this.Client.getDatabase("admin").withCodecRegistry(pojoCodecRegistry);
        this.Collection = this.Database.getCollection("data", DataModel.class);
        if(!collectionExists("data"))
        {
            this.Database.createCollection("data");
        }
    }

    @Async
    public CompletableFuture<String> insert(DataModel dat) {
       Collection.insertOne(dat);
       return CompletableFuture.completedFuture("Done");
    }

    @Async
    public CompletableFuture<Integer> bulkUpsert(List<DataModel> data) {

       /* List<UpdateOneModel<Document>> updateDocuments = new ArrayList<>();
        for (DataModel dat : data) {

            //Finder doc
            Document filterDocument = new Document();
            filterDocument.append("_id", dat.getId());

            //Update doc
            Document updateDocument = new Document();
            Document setDocument = new Document();
            setDocument.append(dat.getId(), dat);

            updateDocument.append("$set", setDocument);

            //Update option
            UpdateOptions updateOptions = new UpdateOptions();
            updateOptions.upsert(true); //if true, will create a new doc in case of unmatched find
            updateOptions.bypassDocumentValidation(true); //set true/false

            //Prepare list of Updates
            updateDocuments.add(
                    new UpdateOneModel<>(
                            filterDocument,
                            updateDocument,
                            updateOptions));

        }

        //Bulk write options
        BulkWriteOptions bulkWriteOptions = new BulkWriteOptions();
        bulkWriteOptions.ordered(false);
        bulkWriteOptions.bypassDocumentValidation(true);
        BulkWriteResult bulkWriteResult = null;

        try {
            //Perform bulk update
            bulkWriteResult = Collection.b
        } catch (BulkWriteException e) {
            //Handle bulkwrite exception
            List<BulkWriteError> bulkWriteErrors = e.getWriteErrors();
            for (BulkWriteError bulkWriteError : bulkWriteErrors) {
                int failedIndex = bulkWriteError.getIndex();
                String failedEntityId = data.get(failedIndex).getId();
                System.out.println("Failed record: " + failedEntityId);
                //handle rollback
            }
        }

        int modified = bulkWriteResult.getModifiedCount();*/

        return CompletableFuture.completedFuture(1);

    }
}
