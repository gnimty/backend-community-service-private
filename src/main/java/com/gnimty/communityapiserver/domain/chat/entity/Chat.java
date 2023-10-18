package com.gnimty.communityapiserver.domain.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("chat")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
	@Id
	private String id;
	private Long chatRoomNo;
	private String senderId;
	private String message;
	private Date sendDate;
	private Integer readCnt;
}
