package com.gnimty.communityapiserver.domain.member.service.dto.response;

import com.gnimty.communityapiserver.domain.prefergamemode.entity.PreferGameMode;
import com.gnimty.communityapiserver.global.constant.GameMode;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PreferGameModeEntry {

	private Long id;
	private GameMode gameMode;
	private Boolean isPreferred;

	public static PreferGameModeEntry from(PreferGameMode preferGameMode) {
		return PreferGameModeEntry.builder()
			.id(preferGameMode.getId())
			.gameMode(preferGameMode.getGameMode())
			.isPreferred(preferGameMode.getIsPreferred())
			.build();
	}
}
