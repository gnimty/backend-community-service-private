package com.gnimty.communityapiserver.domain.member.service.dto.response;

import com.gnimty.communityapiserver.domain.introduction.entity.Introduction;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class IntroductionEntry {

	private Long id;
	private String content;
	private Boolean isMain;

	public static IntroductionEntry from(Introduction introduction) {
		return IntroductionEntry.builder()
			.id(introduction.getId())
			.content(introduction.getContent())
			.isMain(introduction.getIsMain())
			.build();
	}
}
