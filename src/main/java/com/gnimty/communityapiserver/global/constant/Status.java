package com.gnimty.communityapiserver.global.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.stream.Stream;
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

	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public static Status findByCode(String input) {
		return Stream.of(Status.values())
			.filter(c -> c.name().equals(input))
			.findFirst()
			.orElse(null);
	}
}
