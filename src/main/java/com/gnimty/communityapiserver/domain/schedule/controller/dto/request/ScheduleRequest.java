package com.gnimty.communityapiserver.domain.schedule.controller.dto.request;

import com.gnimty.communityapiserver.domain.schedule.service.dto.request.ScheduleServiceRequest;
import com.gnimty.communityapiserver.global.constant.DayOfWeek;
import com.gnimty.communityapiserver.global.validation.annotation.ValidEnum;
import java.util.List;
import javax.validation.Valid;
import lombok.Getter;

@Getter
public class ScheduleRequest {

	@Valid
	private List<ScheduleEntry> schedules;

	public ScheduleServiceRequest toServiceRequest() {
		return ScheduleServiceRequest.builder()
			.schedules(schedules)
			.build();
	}
}
