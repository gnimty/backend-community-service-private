package com.gnimty.communityapiserver.domain.chat.controller;
import com.gnimty.communityapiserver.domain.chat.controller.dto.ChatRoomInfo;
import com.gnimty.communityapiserver.domain.chat.service.ChatService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate template;

    // 채팅의 모든 조회
    @SubscribeMapping("/init_chat")
    public List<ChatRoomInfo> getTotalChatRoomsAndChatsAndOtherUserInfo() {

        List<ChatRoomInfo> chatRoomInfos = chatService.getTotalChats();

        return chatRoomInfos;
    }

    // 채팅방 구독 유도
    @MessageMapping("/user/{otherUserId}")
    public void createChatRoomAndDerive(@DestinationVariable("otherUserId") Long otherUserId) {

        Long chatRoomId = chatService.getChatRoomId(otherUserId);

        Long myUserId = 2L; // 자신의 id (삭제 예정)
        template.convertAndSend("/sub/user/"+myUserId, chatRoomId);

        if (!chatService.isBlock(otherUserId))
            template.convertAndSend("/sub/user/"+otherUserId, chatRoomId);
    }

    // 메세지 전송
    @MessageMapping("/chatRoom/{chatRoomId}")
    public void sendMessage(@DestinationVariable("chatRoomId") Long chatRoomId, String message) {
        template.convertAndSend("/sub/chatRoom/"+chatRoomId, message);
    }

    // 채팅방 나가기
    @SubscribeMapping("/chatRoom/exit/{chatRoomId}")
    public void exitChatRoom(@DestinationVariable("chatRoomId") Long chatRoomId) {
        chatService.exitChatRoom(chatRoomId);
    }



}
