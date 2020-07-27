package com.test.eis.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class TestAppConfig {

    @Bean
    public MongoTemplate mongoOprations(){
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongoClient, "testDatabase"));
    }
}