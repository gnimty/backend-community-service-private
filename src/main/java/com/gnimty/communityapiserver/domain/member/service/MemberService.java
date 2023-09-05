package com.gnimty.communityapiserver.domain.member.service;

import com.gnimty.communityapiserver.domain.member.entity.Member;
import com.gnimty.communityapiserver.domain.member.service.dto.request.OauthLoginServiceRequest;
import com.gnimty.communityapiserver.domain.member.service.utils.GoogleOauthUtil;
import com.gnimty.communityapiserver.domain.member.service.utils.KakaoOauthUtil;
import com.gnimty.communityapiserver.domain.member.service.utils.RiotOauthUtil;
import com.gnimty.communityapiserver.domain.oauthinfo.entity.OauthInfo;
import com.gnimty.communityapiserver.domain.oauthinfo.repository.OauthInfoRepository;
import com.gnimty.communityapiserver.domain.oauthinfo.service.OauthInfoReadService;
import com.gnimty.communityapiserver.domain.riotaccount.entity.RiotAccount;
import com.gnimty.communityapiserver.domain.riotaccount.repository.RiotAccountRepository;
import com.gnimty.communityapiserver.domain.riotaccount.service.RiotAccountReadService;
import com.gnimty.communityapiserver.global.auth.MemberThreadLocal;
import com.gnimty.communityapiserver.global.constant.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final RiotOauthUtil riotOauthUtil;
	private final RiotAccountReadService riotAccountReadService;
	private final RiotAccountRepository riotAccountRepository;
	private final KakaoOauthUtil kakaoOauthUtil;
	private final GoogleOauthUtil googleOauthUtil;
	private final OauthInfoRepository oauthInfoRepository;
	private final OauthInfoReadService oauthInfoReadService;

	public void summonerAccountLink(OauthLoginServiceRequest request) {
		Member member = MemberThreadLocal.get();

		String puuid = "puuid";
		// String puuid = riotOauthUtil.getPuuid(request.getAuthCode());
		// get summoner info by puuid

		riotAccountReadService.throwIfExistsByPuuid(puuid);
		Boolean existsMain = riotAccountReadService.existsByMemberId(member);

		riotAccountRepository.save(RiotAccount.builder()
			.summonerName("summonerName")
			.isMain(!existsMain)
			.queue("queue")
			.lp(100L)
			.division(100)
			.mmr(100L)
			.frequentLane1("")
			.frequentLane2("")
			.frequentChampionId1(1L)
			.frequentChampionId2(1L)
			.frequentChampionId3(1L)
			.puuid(puuid)
			.member(member)
			.build()
		);

		if (!existsMain) {
			member.updateNickname("summonerName");
			member.updateRsoLinked(true);
		}
	}

	public void oauthAdditionalLink(Provider provider, OauthLoginServiceRequest request) {
		final Member member = MemberThreadLocal.get();
		String userEmail;
		if (provider.equals(Provider.KAKAO)) {
			userEmail = kakaoOauthUtil.getKakaoUserEmail(request.getAuthCode());
		} else {
			userEmail = googleOauthUtil.getGoogleUserEmail(request.getAuthCode());
		}
		oauthInfoReadService.throwIfExistsByEmailAndProvider(userEmail, provider);
		createOauthInfoByEmail(member, userEmail, provider);
	}

	private void createOauthInfoByEmail(Member member, String userEmail, Provider provider) {
		oauthInfoRepository.save(OauthInfo.builder()
			.provider(provider)
			.email(userEmail)
			.member(member)
			.build());
	}
}
