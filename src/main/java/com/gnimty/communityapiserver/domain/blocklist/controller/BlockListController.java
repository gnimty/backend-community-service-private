package com.gnimty.communityapiserver.domain.blocklist.controller;

import com.gnimty.communityapiserver.domain.blocklist.controller.dto.response.BlockListReadResponse;
import com.gnimty.communityapiserver.domain.blocklist.service.BlockListReadService;
import com.gnimty.communityapiserver.domain.blocklist.service.dto.response.BlockListReadServiceResponse;
import com.gnimty.communityapiserver.global.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/{member_id}/blocklist")
public class BlockListController {

	private final BlockListReadService blockListReadService;

	@GetMapping
	public CommonResponse<BlockListReadResponse> readBlockList() {
		BlockListReadServiceResponse response = blockListReadService.readBlockList();
		return CommonResponse.success(BlockListReadResponse.from(response));
	}
}
