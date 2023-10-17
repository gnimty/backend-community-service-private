package com.gnimty.communityapiserver.domain.chat.controller;

import com.gnimty.communityapiserver.domain.chat.controller.dto.ChatMessageDto;
import com.gnimty.communityapiserver.domain.chat.repository.ChatRoomRepository;
import com.gnimty.communityapiserver.domain.chat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MessageController {

}
