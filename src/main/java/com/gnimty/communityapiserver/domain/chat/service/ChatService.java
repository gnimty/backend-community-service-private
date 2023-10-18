package com.gnimty.communityapiserver.domain.chat.service;

import com.gnimty.communityapiserver.domain.chat.controller.dto.ChatRoomInfo;
import com.gnimty.communityapiserver.domain.chat.repository.ChatRoomRepository;
import com.gnimty.communityapiserver.global.auth.JwtProvider;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    public List<ChatRoomInfo> getTotalChats() {
        return null;
    }

    public Long getChatRoomId(Long otherUserId) {
        Long chatRoomId = 1L;
        return chatRoomId;
    }

    public boolean isBlock(Long otherUserId) {
        // 로직

        return false; // 삭제 예정
    }

    public void exitChatRoom(Long chatRoomId) {
        // 채팅방 나감 처리
    }

}
