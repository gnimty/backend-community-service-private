package com.gnimty.communityapiserver.domain.chat.repository;


import com.gnimty.communityapiserver.domain.chat.entity.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<Chat, String> {
    // 채팅방 번호와 내 정보로 채팅치기


}
