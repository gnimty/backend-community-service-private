package com.gnimty.communityapiserver.domain.member.service;

import com.gnimty.communityapiserver.domain.member.entity.Member;
import com.gnimty.communityapiserver.domain.member.repository.MemberRepository;
import com.gnimty.communityapiserver.domain.member.service.dto.request.SignupServiceRequest;
import com.gnimty.communityapiserver.global.constant.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final MemberReadService memberReadService;
	private final PasswordEncoder passwordEncoder;
	private static final String NICKNAME_PREFIX = "익명의소환사#";

	@Transactional
	public void signup(SignupServiceRequest request) {
		memberReadService.throwIfExistByEmail(request.getEmail());

		// 이메일 인증 확인

		Member member = Member.builder()
			.rsoLinked(false)
			.email(request.getEmail())
			.password(encodePassword(request.getPassword()))
			.favoriteChampionID(null)
			.status(Status.OFFLINE)
			.build();

		memberRepository.save(member);
		member.updateNickname(generateTemporaryNickname(member.getId()));

		// 기존 인증 정보들 삭제
	}

	private String generateTemporaryNickname(Long id) {
		return NICKNAME_PREFIX + id;
	}

	private String encodePassword(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}
}
