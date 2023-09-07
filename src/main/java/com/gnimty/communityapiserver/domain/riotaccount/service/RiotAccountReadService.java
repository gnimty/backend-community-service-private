package com.gnimty.communityapiserver.domain.riotaccount.service;

import com.gnimty.communityapiserver.domain.member.entity.Member;
import com.gnimty.communityapiserver.domain.riotaccount.entity.RiotAccount;
import com.gnimty.communityapiserver.domain.riotaccount.repository.RiotAccountRepository;
import com.gnimty.communityapiserver.global.exception.BaseException;
import com.gnimty.communityapiserver.global.exception.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RiotAccountReadService {

	private final RiotAccountRepository riotAccountRepository;

	public void throwIfExistsByPuuid(String puuid) {
		if (riotAccountRepository.existsByPuuid(puuid)) {
			throw new BaseException(ErrorCode.ALREADY_LINKED_SUMMONER);
		}
	}

	public Boolean existsByMemberId(Member member) {
		return riotAccountRepository.existsByMember(member);
	}

	public List<RiotAccount> findByMember(Member member) {
		return riotAccountRepository.findByMember(member);
	}
}
