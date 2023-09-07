package com.gnimty.communityapiserver.domain.introduction.service;

import com.gnimty.communityapiserver.domain.introduction.entity.Introduction;
import com.gnimty.communityapiserver.domain.introduction.repository.IntroductionRepository;
import com.gnimty.communityapiserver.domain.member.entity.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IntroductionReadService {

	private final IntroductionRepository introductionRepository;

	public List<Introduction> findByMember(Member member) {
		return introductionRepository.findByMember(member);
	}
}
