package com.gnimty.communityapiserver.domain.chat.repository;

import com.gnimty.communityapiserver.domain.chat.entity.ChatRoom;
import com.gnimty.communityapiserver.domain.chat.entity.User;

import java.util.List;

public interface ChatRoomRepositoryCustom {
//    ChatRoom save(ChatRoom chatRoom);
    List<ChatRoom> findByUser(User user);

    List<ChatRoom> findByUsers(User user1, User user2);

}