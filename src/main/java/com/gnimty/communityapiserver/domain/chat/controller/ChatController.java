package com.gnimty.communityapiserver.domain.chat.controller;
import com.gnimty.communityapiserver.domain.chat.controller.dto.ChatRoomInfo;
import com.gnimty.communityapiserver.domain.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate template;

    @SubscribeMapping("/init_total_chats")
    public List<ChatRoomInfo> getTotalChats() {

        // TODO: 서비스 단에서 사용자 id를 통해서 가져올 예정 (밑의 코드는 테스트 용)
        //List<ChatRoomInfo> chatRoomInfos = getTotalChats();

        List<ChatRoomInfo> chatRoomInfos = new ArrayList<>();
        ChatRoomInfo chatRoomInfo1 = new ChatRoomInfo(1L, 2L);
        ChatRoomInfo chatRoomInfo2 = new ChatRoomInfo(3L, 4L);
        chatRoomInfos.add(chatRoomInfo1);
        chatRoomInfos.add(chatRoomInfo2);

        return chatRoomInfos;
    }

//    @MessageMapping("/user/{other_id}")
//    public void userSub() {
//        // service
//            // 채팅방 id를 얻어옴
//            // 구독 요청을 보내야함
//        template.
//
//    }


}
