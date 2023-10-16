package com.gnimty.communityapiserver.domain.chat.handler.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ChatRequest {

	private ChatType chatType;

	private String message;

	private Long chatRoomId;

	private Long receiverId;

}
