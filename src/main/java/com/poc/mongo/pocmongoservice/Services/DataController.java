package com.poc.mongo.pocmongoservice.Services;

import com.poc.mongo.pocmongoservice.Models.DataModel;
import com.poc.mongo.pocmongoservice.Repository.MongoDB.MongoDbRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/api")
public class DataController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    MongoDbRepository repo;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addOne(@RequestBody DataModel dat) throws ExecutionException, InterruptedException {
        LOG.info("Started insert operation");
        long start = System.currentTimeMillis();
        CompletableFuture<String> add = repo.insert(dat);
        String res = add.get();
        LOG.info("Elapsed time: " + (System.currentTimeMillis() - start));
        LOG.info("Insert record "+res);
        return res;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String addData(@RequestBody List<DataModel> dat) throws ExecutionException, InterruptedException {
        LOG.info("Started upsert operation");
        long start = System.currentTimeMillis();
        repo.bulkUpsert(dat);
        Double time = ((double)(System.currentTimeMillis() - start)/1000);
        return time.toString();
    }

}
