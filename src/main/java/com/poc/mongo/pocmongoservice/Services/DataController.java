package com.poc.mongo.pocmongoservice.Services;

import com.mongodb.client.MongoCollection;
import com.poc.mongo.pocmongoservice.Models.DataModel;
import com.poc.mongo.pocmongoservice.Repository.MongoDB.MongoDbRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class DataController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    MongoDbRepository repo;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public DataModel addData(@RequestBody List<DataModel> dat) {
        LOG.info("Started upsert operation");
        repo.bulkUpsert(dat);
    }

}
