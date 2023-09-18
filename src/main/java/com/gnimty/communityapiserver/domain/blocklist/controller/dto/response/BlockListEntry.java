package com.gnimty.communityapiserver.domain.blocklist.controller.dto.response;

import com.gnimty.communityapiserver.domain.blocklist.entity.BlockList;
import com.gnimty.communityapiserver.global.constant.Status;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BlockListEntry {

	private Long id;
	private Long blockerId;
	private LocalDate date;
	private String nickname;
	private Status status;
	private String memo;

	public static BlockListEntry from(BlockList blockList) {
		return BlockListEntry.builder()
			.id(blockList.getBlocked().getId())
			.blockerId(blockList.getBlocker().getId())
			.date(blockList.getCreatedAt().toLocalDate())
			.nickname(blockList.getBlocked().getNickname())
			.status(blockList.getBlocked().getStatus())
			.memo(blockList.getMemo())
			.build();
	}
}
