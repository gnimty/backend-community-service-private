package com.gnimty.communityapiserver.domain.chat.repository;

import com.gnimty.communityapiserver.domain.chat.entity.ChatRoom;
import com.gnimty.communityapiserver.domain.chat.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@RequiredArgsConstructor
public class ChatRoomRepositoryImpl implements ChatRoomRepositoryCustom {
    @Autowired
    private final MongoTemplate mongoTemplate;

    @Override
    public List<ChatRoom> findByUser(User user) {
        Query query = new Query(Criteria.where("participants.user").is(user));
        List<ChatRoom> chatRooms = mongoTemplate.find(query, ChatRoom.class);
        return chatRooms;
    }

    @Override
    public List<ChatRoom> findByUsers(User user1, User user2) {
        return null;
    }
//    @Override
//    public ChatRoom save(ChatRoom chatRoom) {
//        // 0. participant가 둘다 속해 있는 chatRoom이 없는지 확인
//
//        // 1. unique한 chatRoomNo를 찾기
//
//        // 2. chatRoom에 chatRoom Set
//
//        // 3. 저장하고 리턴₩
//
//        return null;
//    }




}