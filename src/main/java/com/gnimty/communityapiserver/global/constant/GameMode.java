package com.gnimty.communityapiserver.global.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.stream.Stream;
import lombok.Getter;

@Getter
public enum GameMode {
	RANK_SOLO,
	RANK_FLEX,
	BLIND;

	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public static GameMode findByCode(String input) {
		return Stream.of(GameMode.values())
			.filter(c -> c.name().equals(input))
			.findFirst()
			.orElse(null);
	}
}
