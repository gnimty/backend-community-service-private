package com.gnimty.communityapiserver.global.constant;

import lombok.Getter;

@Getter
public enum Auth {

	// 10분
	ACCESS_TOKEN_EXPIRATION(1_000L * 60 * 10),
	// 2주
	REFRESH_TOKEN_EXPIRATION(1_000L * 60 * 60 * 24 * 14),

	SUBJECT_ACCESS_TOKEN("accessToken"),
	SUBJECT_REFRESH_TOKEN("refreshToken"),
	BEARER("Bearer ");

	private long expiration;
	private String content;

	Auth(long expiration) {
		this.expiration = expiration;
	}

	Auth(String content) {
		this.content = content;
	}
}
