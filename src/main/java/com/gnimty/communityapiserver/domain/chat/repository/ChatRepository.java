package com.gnimty.communityapiserver.domain.chat.repository;


import com.gnimty.communityapiserver.domain.chat.entity.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<Chat, String> {
}
