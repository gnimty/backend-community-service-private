package com.gnimty.communityapiserver.domain.block.controller;

import com.gnimty.communityapiserver.domain.block.controller.dto.response.BlockReadResponse;
import com.gnimty.communityapiserver.domain.block.service.BlockReadService;
import com.gnimty.communityapiserver.domain.block.service.dto.response.BlockReadServiceResponse;
import com.gnimty.communityapiserver.global.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/{member_id}/block")
public class BlockController {

	private final BlockReadService blockReadService;

	@GetMapping
	public CommonResponse<BlockReadResponse> readBlocks() {
		BlockReadServiceResponse response = blockReadService.readBlocks();
		return CommonResponse.success(BlockReadResponse.from(response));
	}
}
