package com.gnimty.communityapiserver.domain.chat.controller;

import com.gnimty.communityapiserver.domain.chat.entity.ChatRoom;
import com.gnimty.communityapiserver.domain.chat.entity.ChatUser;
import com.gnimty.communityapiserver.domain.chat.entity.User;
import com.gnimty.communityapiserver.domain.chat.repository.ChatRoomRepository;
import com.gnimty.communityapiserver.domain.chat.repository.UserRepository;
import com.gnimty.communityapiserver.domain.member.entity.Member;
import com.gnimty.communityapiserver.global.constant.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageController {

    private final SimpMessageSendingOperations sendingOperations;
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

//    @MessageMapping("/chat/message")
//    public String sendMessage(@Valid ChatMessageDto message) {
//
//        if (message.getChatRoomId()==2) {
//            log.info("2가 들어옴");
//            return "get out";
//        }
//
//        log.info("{}", message);
//
//        sendingOperations.convertAndSend("/pub/chat/room/" + message.getChatRoomId(), message.getMessage());
//        return null;
//    }
//
//    @MessageMapping("/findChatRoom")
//    public String chatRoom(Long receiverId) {
//        log.info("findChatRoom으로 들어옴  {}", receiverId);
//        ChatRoom chatRoom = findChatRoom(receiverId);
//
//        log.info("findChatRoom = {}", chatRoom.getId());
//        return String.valueOf(chatRoom.getId());
//    }

    @MessageMapping("/beforetest")
    @SendTo("/sub/test")
    public String test1(String str){
        log.info("test2---" + str);
        return "what is this?";
    }

    @SubscribeMapping("/sub/test")
    public String test2() {
        log.info("test로 들어옴");
        return "i don't know";
    }

//    private ChatRoom findChatRoom(Long receiverId) {
//        User user1;
//        User user2;
//        ChatRoom chatRoom;
//
//        List<Long> usersId = new ArrayList<>();
//        usersId.add(1L); // 본인
//        usersId.add(receiverId);
//        Optional<ChatRoom> findChatRoom = chatRoomRepository.findByChatUsersUserIdIn(usersId);
//
//        if (findChatRoom.isEmpty()) {
//            Optional<User> findSender = userRepository.findByUserId(1L);
//            if (findSender.isEmpty()) {
//                user1 = userRepository.save(new User(1L, "사용자1", Status.ONLINE));
//            }
//
//            else {
//                user1 = findSender.get();
//            }
//
//            Optional<User> findReceiver = userRepository.findByUserId(receiverId);
//            if (findReceiver.isEmpty()) {
//                user2 = userRepository.save(new User(receiverId, "사용자2", Status.ONLINE));
//            }
//
//            else {
//                user2 = findReceiver.get();
//            }
//
//            ChatUser chatUser1 = new ChatUser(user1.getUserId(), LocalDateTime.now());
//            ChatUser chatUser2 = new ChatUser(user2.getUserId(), LocalDateTime.now());
//
//            List<ChatUser> chatUsers = new ArrayList<>();
//            chatUsers.add(chatUser1);
//            chatUsers.add(chatUser2);
//
//            chatRoom = chatRoomRepository.save(new ChatRoom(chatUsers));
//        }
//
//        else {
//            chatRoom = findChatRoom.get();
//        }
//        return chatRoom;
//    }
}
