package com.gnimty.communityapiserver.domain.chat.service;

import com.gnimty.communityapiserver.domain.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;

//    public List<ChatRoomSimpleInfoDto> getChatRoomSimpleInfo(Long userId) {
//    }
}
