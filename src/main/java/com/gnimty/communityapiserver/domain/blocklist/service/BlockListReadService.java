package com.gnimty.communityapiserver.domain.blocklist.service;

import com.gnimty.communityapiserver.domain.blocklist.controller.dto.response.BlockListEntry;
import com.gnimty.communityapiserver.domain.blocklist.entity.BlockList;
import com.gnimty.communityapiserver.domain.blocklist.repository.BlockListRepository;
import com.gnimty.communityapiserver.domain.blocklist.service.dto.response.BlockListReadServiceResponse;
import com.gnimty.communityapiserver.domain.member.entity.Member;
import com.gnimty.communityapiserver.global.auth.MemberThreadLocal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BlockListReadService {

	private final BlockListRepository blockListRepository;

	public BlockListReadServiceResponse readBlockList() {
		Member member = MemberThreadLocal.get();
		List<BlockList> blockList = blockListRepository.findByBlocker(member);
		return BlockListReadServiceResponse.builder()
			.blockList(blockList.stream()
				.map(BlockListEntry::from)
				.toList()
			).build();
	}
}