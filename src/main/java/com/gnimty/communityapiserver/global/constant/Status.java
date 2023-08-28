package com.gnimty.communityapiserver.global.constant;

import lombok.Getter;

@Getter
public enum Status {

	ONLINE("온라인"),
	OFFLINE("오프라인"),
	AWAY("자리비움");

	private final String status;

	Status(String stats) {
		this.status = stats;
	}
}
