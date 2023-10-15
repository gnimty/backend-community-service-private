package com.gnimty.communityapiserver.domain.chat.entity;

import java.time.LocalDateTime;

public class Chat {

	private Long userId;
	private String message;
	private LocalDateTime timeSend;

	public Chat(Long userId, String message, LocalDateTime timeSend) {
		this.userId = userId;
		this.message = message;
		this.timeSend = timeSend;
	}
}
