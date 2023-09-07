package com.gnimty.communityapiserver.domain.member.service.dto.response;

import com.gnimty.communityapiserver.domain.schedule.entity.Schedule;
import java.time.DayOfWeek;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ScheduleEntry {

	private Long id;
	private DayOfWeek dayOfWeek;
	private Integer startTime;
	private Integer endTime;
	private Boolean isActivated;

	public static ScheduleEntry from(Schedule schedule) {
		return ScheduleEntry.builder()
			.id(schedule.getId())
			.dayOfWeek(schedule.getDayOfWeek())
			.startTime(schedule.getStartTime())
			.endTime(schedule.getEndTime())
			.isActivated(schedule.getIsActivated())
			.build();
	}
}
