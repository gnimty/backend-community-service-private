package com.gnimty.communityapiserver.domain.chat.entity;

import com.gnimty.communityapiserver.global.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Document(collection = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@Field(name = "_id")
	private Long userId;
	private String nickname;
	private Status status;

	public User(Long userId) {
		this.userId = userId;
	}

	public void updateUser(String nickname, Status status) {
		this.nickname = nickname;
		this.status = status;
	}
}
