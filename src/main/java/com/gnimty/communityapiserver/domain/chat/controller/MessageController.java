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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MessageController {

    private final SimpMessageSendingOperations sendingOperations;
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;


    // Test 용도 (삭제 예정)
    @SubscribeMapping("/test")
    public String test2() {
        return "here";
    }
}
