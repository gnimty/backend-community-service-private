package com.gnimty.communityapiserver.global.constant;

import lombok.Getter;

@Getter
public enum KeyPrefix {

	NICKNAME("익명의소환사#"),
	REFRESH("[REFRESH]"),
	EMAIL("[EMAIL]");

	private final String prefix;

	KeyPrefix(String prefix) {
		this.prefix = prefix;
	}
}
