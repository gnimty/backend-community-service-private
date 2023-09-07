package com.gnimty.communityapiserver.global.constant;

import lombok.Getter;

@Getter
public enum GameMode {
	RANK_SOLO("솔로 랭크"),
	RANK_FLEX("자유 랭크"),
	BLIND("일반 게임/칼바람 나락");

	private final String mode;

	GameMode(String mode) {
		this.mode = mode;
	}
}
