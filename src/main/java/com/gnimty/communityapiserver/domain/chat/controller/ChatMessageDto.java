package com.gnimty.communityapiserver.domain.chat.controller;

import com.gnimty.communityapiserver.global.exception.ErrorCode;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ChatMessageDto {
    private Long chatRoomId;
    private Long receiverId;

    @NotNull(message = ErrorCode.ErrorMessage.INVALID_INPUT_VALUE)
    private String message;
}
