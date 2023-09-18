package com.gnimty.communityapiserver.domain.blocklist.controller.dto.response;

import com.gnimty.communityapiserver.domain.blocklist.service.dto.response.BlockListReadServiceResponse;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BlockListReadResponse {

	private final List<BlockListEntry> blockList;

	public static BlockListReadResponse from(BlockListReadServiceResponse response) {
		return BlockListReadResponse.builder()
			.blockList(response.getBlockList())
			.build();
	}
}
