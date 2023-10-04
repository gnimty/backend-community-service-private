package com.gnimty.communityapiserver.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
@Profile({"local", "dev"})
public class MongoDBConfiguration {

	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoDatabaseFactory());
	}

	@Bean
	public SimpleMongoClientDatabaseFactory mongoDatabaseFactory() {
		return new SimpleMongoClientDatabaseFactory("mongodb://localhost:27017/community");
	}
}