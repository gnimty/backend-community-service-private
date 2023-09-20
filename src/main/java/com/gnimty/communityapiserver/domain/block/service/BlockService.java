package com.gnimty.communityapiserver.domain.block.service;

import com.gnimty.communityapiserver.domain.block.entity.Block;
import com.gnimty.communityapiserver.domain.block.repository.BlockRepository;
import com.gnimty.communityapiserver.domain.block.service.dto.request.BlockServiceRequest;
import com.gnimty.communityapiserver.domain.member.entity.Member;
import com.gnimty.communityapiserver.domain.member.service.MemberReadService;
import com.gnimty.communityapiserver.global.auth.MemberThreadLocal;
import com.gnimty.communityapiserver.global.exception.BaseException;
import com.gnimty.communityapiserver.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BlockService {

	private final MemberReadService memberReadService;
	private final BlockRepository blockRepository;
	private final BlockReadService blockReadService;

	public void doBlock(BlockServiceRequest request) {
		Member member = MemberThreadLocal.get();
		Member blocked = memberReadService.findById(request.getId());
		if (blockReadService.existsByBlockerAndBlocked(member, blocked)) {
			throw new BaseException(ErrorCode.ALREADY_BLOCKED_MEMBER);
		}
		blockRepository.save(createBlockEntity(request, member, blocked));
	}

	private Block createBlockEntity(BlockServiceRequest request, Member member, Member blocked) {
		return Block.builder()
			.blocker(member)
			.blocked(blocked)
			.memo(request.getMemo())
			.build();
	}
}
