package com.gnimty.communityapiserver.domain.member.service;

import com.gnimty.communityapiserver.domain.member.controller.dto.response.AuthToken;
import com.gnimty.communityapiserver.domain.member.entity.Member;
import com.gnimty.communityapiserver.domain.member.repository.MemberRepository;
import com.gnimty.communityapiserver.domain.member.service.dto.request.OauthLoginServiceRequest;
import com.gnimty.communityapiserver.domain.member.service.dto.request.LoginServiceRequest;
import com.gnimty.communityapiserver.domain.member.service.dto.request.SignupServiceRequest;
import com.gnimty.communityapiserver.domain.member.service.utils.GoogleOauthUtil;
import com.gnimty.communityapiserver.domain.member.service.utils.KakaoOauthUtil;
import com.gnimty.communityapiserver.domain.oauthinfo.entity.OauthInfo;
import com.gnimty.communityapiserver.domain.oauthinfo.repository.OauthInfoRepository;
import com.gnimty.communityapiserver.global.auth.JwtProvider;
import com.gnimty.communityapiserver.global.constant.Auth;
import com.gnimty.communityapiserver.global.constant.Provider;
import com.gnimty.communityapiserver.global.constant.Status;
import com.gnimty.communityapiserver.global.exception.BaseException;
import com.gnimty.communityapiserver.global.exception.ErrorCode;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final MemberRepository memberRepository;
	private final OauthInfoRepository oauthInfoRepository;
	private final MemberReadService memberReadService;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;
	private final StringRedisTemplate redisTemplate;
	private final KakaoOauthUtil kakaoOauthUtil;
	private final GoogleOauthUtil googleOauthUtil;

	private static final String NICKNAME_PREFIX = "익명의소환사#";
	private static final String REFRESH_PREFIX = "[REFRESH]";

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

	@Transactional
	public AuthToken login(LoginServiceRequest request) {
		Member member = memberReadService.findByEmailOrElseThrow(request.getEmail());
		throwIfMismatchPassword(request.getPassword(), member.getPassword());

		AuthToken authToken = generateTokenPair(member);
		saveRefreshTokenInRedis(member, authToken.getRefreshToken());
		return authToken;
	}

	@Transactional
	public AuthToken kakaoLogin(OauthLoginServiceRequest request) {
		String userEmail = kakaoOauthUtil.getKakaoUserEmail(request.getAuthCode());

		Member member = oauthInfoRepository.findByEmail(userEmail)
			.map(OauthInfo::getMember)
			.orElseGet(() -> createMemberByEmail(userEmail, Provider.KAKAO));
		AuthToken authToken = generateTokenPair(member);
		saveRefreshTokenInRedis(member, authToken.getRefreshToken());
		return authToken;
	}

	public AuthToken googleLogin(OauthLoginServiceRequest request) {
		String googleUserEmail = googleOauthUtil.getGoogleUserEmail(request.getAuthCode());

		Member member = oauthInfoRepository.findByEmail(googleUserEmail)
			.map(OauthInfo::getMember)
			.orElseGet(() -> createMemberByEmail(googleUserEmail, Provider.GOOGLE));
		AuthToken authToken = generateTokenPair(member);
		saveRefreshTokenInRedis(member, authToken.getRefreshToken());
		return authToken;
	}

	private Member createMemberByEmail(String userEmail, Provider provider) {
		Member member = Member.builder()
			.rsoLinked(false)
			.favoriteChampionID(null)
			.status(Status.OFFLINE)
			.build();
		memberRepository.save(member);

		OauthInfo oauthInfo = OauthInfo.builder()
			.provider(provider)
			.email(userEmail)
			.member(member)
			.build();
		oauthInfoRepository.save(oauthInfo);
		return member;
	}

	private String generateTemporaryNickname(Long id) {
		return NICKNAME_PREFIX + id;
	}

	private String encodePassword(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}

	private void throwIfMismatchPassword(String rawPassword, String encodedPassword) {
		if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
			throw new BaseException(ErrorCode.INVALID_LOGIN);
		}
	}

	private AuthToken generateTokenPair(Member member) {
		String accessToken = Auth.BEARER.getContent() + jwtProvider.generateToken(member.getId(),
			Auth.ACCESS_TOKEN_EXPIRATION.getExpiration(), Auth.SUBJECT_ACCESS_TOKEN.getContent());
		String refreshToken = Auth.BEARER.getContent() + jwtProvider.generateToken(member.getId(),
			Auth.REFRESH_TOKEN_EXPIRATION.getExpiration(), Auth.SUBJECT_REFRESH_TOKEN.getContent());

		return AuthToken.builder()
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.build();
	}

	private void saveRefreshTokenInRedis(Member member, String refreshToken) {
		ValueOperations<String, String> stringValueOperations = redisTemplate.opsForValue();
		String key = getRefreshKey(member.getId());

		stringValueOperations.set(key, refreshToken.replaceAll(Auth.BEARER.getContent(), ""));
		stringValueOperations.getAndExpire(
			key,
			Auth.REFRESH_TOKEN_EXPIRATION.getExpiration(),
			TimeUnit.MILLISECONDS
		);
	}

	private String getRefreshKey(Long id) {
		return REFRESH_PREFIX + id;
	}
}
