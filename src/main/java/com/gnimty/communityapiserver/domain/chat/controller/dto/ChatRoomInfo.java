package com.gnimty.communityapiserver.domain.chat.controller.dto;

import lombok.Data;

@Data
public class ChatRoomInfo {
    private Long chatRoomId;
    private Long otherUserId;

    public ChatRoomInfo(Long chatRoomId, Long otherUserId) {
        this.chatRoomId = chatRoomId;
        this.otherUserId = otherUserId;
    }

    // ...
}
