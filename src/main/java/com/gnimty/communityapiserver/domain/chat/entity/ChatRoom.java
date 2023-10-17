package com.gnimty.communityapiserver.domain.chat.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Set;

@Document(collection = "chatRoom")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatRoom {
	@Id
	private String id;
	private Long chatRoomNo;
	private Set<Participant> participants;
	private Date createdDate;

	@Getter
	@Setter
	@AllArgsConstructor
	@ToString
	public static class Participant{
		@DBRef
		private User user;
		private Date exitDate;
		private Status status = Status.UNBLOCK;
	}
}