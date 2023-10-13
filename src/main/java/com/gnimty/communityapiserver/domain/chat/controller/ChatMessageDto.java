package com.gnimty.communityapiserver.domain.chat.controller;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ChatMessageDto {
    private Long chatRoomId;
    private Long receiverId;

    @NotNull
    private String message;
}
