package com.gnimty.communityapiserver.global.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.stream.Stream;
import lombok.Getter;

@Getter
public enum DayOfWeek {

	SUNDAY,
	MONDAY,
	TUESDAY,
	WEDNESDAY,
	THURSDAY,
	FRIDAY,
	SATURDAY;

	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public static DayOfWeek findByCode(String input) {
		return Stream.of(DayOfWeek.values())
			.filter(c -> c.name().equals(input))
			.findFirst()
			.orElse(null);
	}

}
