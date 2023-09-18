package com.gnimty.communityapiserver.domain.blocklist.service.dto.response;

import com.gnimty.communityapiserver.domain.blocklist.controller.dto.response.BlockListEntry;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BlockListReadServiceResponse {

	private List<BlockListEntry> blockList;
}
