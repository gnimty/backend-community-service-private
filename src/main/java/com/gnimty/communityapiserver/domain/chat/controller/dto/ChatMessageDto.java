package com.gnimty.communityapiserver.domain.chat.controller.dto;

import com.gnimty.communityapiserver.global.exception.ErrorCode;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChatMessageDto {

	private Long chatRoomId;
	private Long receiverId;

	@NotNull(message = ErrorCode.ErrorMessage.INVALID_INPUT_VALUE)
	private String message;
}
