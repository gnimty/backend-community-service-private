package com.gnimty.communityapiserver.domain.chat.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
public class ChatUser {

    @Id
    @Field(name = "_id")
    private Long userId;
    private LocalDateTime timeEntered;

    public ChatUser() {
    }

    public ChatUser(Long id, LocalDateTime timeEntered) {
        this.userId = id;
        this.timeEntered = timeEntered;
    }

}
