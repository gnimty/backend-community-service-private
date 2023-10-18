package com.gnimty.communityapiserver.global.config;

import static java.util.Collections.singletonList;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
    basePackages = {"com.gnimty.communityapiserver.domain.chat.repository"}
)
@RequiredArgsConstructor
@Profile({"dev", "prod"})
public class MongoDBConfiguration extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.profiles.active}")
    private String profile;

    @Bean
    @ConfigurationProperties(prefix = "spring.data.mongodb")
    public MongoProperties mongoProperties() {
        return new MongoProperties();
    }

    @Bean
    public MongoClient mongoClient(MongoProperties mongoProperties) {
        MongoCredential credential = MongoCredential
            .createCredential(mongoProperties.getUsername(),
                mongoProperties.getAuthenticationDatabase(), mongoProperties.getPassword());

        return MongoClients.create(MongoClientSettings.builder()
            .applyToClusterSettings(builder -> builder
                .hosts(singletonList(
                    new ServerAddress(mongoProperties.getHost(), mongoProperties.getPort()))))
            .credential(credential)
            .build());
    }



    @Bean
    public MongoTemplate mongoTemplate(MongoProperties mongoProperties, MongoClient mongoClient) {
        MongoDatabaseFactory mongoDatabaseFactory = new SimpleMongoClientDatabaseFactory(
            mongoClient, mongoProperties.getDatabase());
        return new MongoTemplate(mongoDatabaseFactory);
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    public boolean autoIndexCreation() {
        return true;
    }
}
