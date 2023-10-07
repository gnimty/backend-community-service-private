package com.gnimty.communityapiserver.domain.chat.handler.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;


@Data
@NoArgsConstructor
public class ChatRequest {

    private ChatType chatType;

    private String message;

    private Long chatRoomId;

    private Long receiverId;

}
