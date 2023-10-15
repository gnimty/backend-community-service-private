package com.gnimty.communityapiserver.domain.chat.entity;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatUser {

	@Id
	@Field(name = "_id")
	private Long userId;
	private LocalDateTime timeEntered;
}
