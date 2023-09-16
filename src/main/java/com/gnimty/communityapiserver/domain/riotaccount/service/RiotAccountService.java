package com.gnimty.communityapiserver.domain.riotaccount.service;

import com.gnimty.communityapiserver.domain.member.controller.dto.request.SummonerUpdateEntry;
import com.gnimty.communityapiserver.domain.member.service.dto.request.SummonerUpdateServiceRequest;
import com.gnimty.communityapiserver.domain.riotaccount.entity.RiotAccount;
import com.gnimty.communityapiserver.domain.riotaccount.repository.RiotAccountJdbcRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RiotAccountService {

	private final RiotAccountReadService riotAccountReadService;
	private final RiotAccountJdbcRepository riotAccountJdbcRepository;

	public void updateSummoners(SummonerUpdateServiceRequest request) {
		List<SummonerUpdateEntry> summonerUpdateEntries = request.getSummonerUpdates()
			.stream()
			.filter(v -> riotAccountReadService.findByPuuid(v.getPuuid()) != null)
			.toList();
		List<RiotAccount> existRiotAccounts = riotAccountReadService.findByPuuids(
			summonerUpdateEntries.stream().map(SummonerUpdateEntry::getPuuid).toList());
		riotAccountJdbcRepository.processBatchUpdate(existRiotAccounts, summonerUpdateEntries);
	}
}
