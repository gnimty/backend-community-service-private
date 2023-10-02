package com.gnimty.communityapiserver.domain.chat.entity;

import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "chatRoom")
public class ChatRoom {

    @Field(name = "_id")
    @Id
    private ObjectId id;
    private List<Chat> chats;

    private List<ChatUser> chatUsers;

    public ChatRoom() {
    }

    public ChatRoom(List<ChatUser> chatUsers) {
        this.chats = new ArrayList<>();
        this.chatUsers = chatUsers;
    }

    public ObjectId getId(){
        return this.id;
    }


    public void addChat(Chat chat) {
        this.chats.add(chat);
    }

    public List<ChatUser> getChatUsers(){
        return chatUsers;
    }

    public List<Chat> getChats(){
        return chats;
    }

}
