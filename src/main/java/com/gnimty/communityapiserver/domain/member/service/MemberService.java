package com.gnimty.communityapiserver.domain.member.service;

import com.gnimty.communityapiserver.domain.introduction.entity.Introduction;
import com.gnimty.communityapiserver.domain.introduction.repository.IntroductionRepository;
import com.gnimty.communityapiserver.domain.introduction.service.IntroductionReadService;
import com.gnimty.communityapiserver.domain.member.entity.Member;
import com.gnimty.communityapiserver.domain.member.repository.MemberRepository;
import com.gnimty.communityapiserver.domain.member.service.dto.request.MyProfileUpdateServiceRequest;
import com.gnimty.communityapiserver.domain.member.service.dto.request.OauthLoginServiceRequest;
import com.gnimty.communityapiserver.domain.member.service.dto.request.PasswordEmailVerifyServiceRequest;
import com.gnimty.communityapiserver.domain.member.service.dto.request.PasswordUpdateServiceRequest;
import com.gnimty.communityapiserver.domain.member.service.dto.response.IntroductionEntry;
import com.gnimty.communityapiserver.domain.member.service.dto.response.MyProfileServiceResponse;
import com.gnimty.communityapiserver.domain.member.service.dto.response.OauthInfoEntry;
import com.gnimty.communityapiserver.domain.member.service.dto.response.PreferGameModeEntry;
import com.gnimty.communityapiserver.domain.member.service.dto.response.RiotAccountEntry;
import com.gnimty.communityapiserver.domain.member.service.dto.response.RiotDependentInfo;
import com.gnimty.communityapiserver.domain.schedule.controller.dto.request.ScheduleEntry;
import com.gnimty.communityapiserver.domain.member.service.utils.GoogleOauthUtil;
import com.gnimty.communityapiserver.domain.member.service.utils.KakaoOauthUtil;
import com.gnimty.communityapiserver.domain.member.service.utils.RiotOauthUtil;
import com.gnimty.communityapiserver.domain.oauthinfo.entity.OauthInfo;
import com.gnimty.communityapiserver.domain.oauthinfo.repository.OauthInfoRepository;
import com.gnimty.communityapiserver.domain.oauthinfo.service.OauthInfoReadService;
import com.gnimty.communityapiserver.domain.prefergamemode.entity.PreferGameMode;
import com.gnimty.communityapiserver.domain.prefergamemode.service.PreferGameModeReadService;
import com.gnimty.communityapiserver.domain.riotaccount.entity.RiotAccount;
import com.gnimty.communityapiserver.domain.riotaccount.repository.RiotAccountRepository;
import com.gnimty.communityapiserver.domain.riotaccount.service.RiotAccountReadService;
import com.gnimty.communityapiserver.domain.schedule.entity.Schedule;
import com.gnimty.communityapiserver.domain.schedule.service.ScheduleReadService;
import com.gnimty.communityapiserver.global.auth.MemberThreadLocal;
import com.gnimty.communityapiserver.global.constant.Auth;
import com.gnimty.communityapiserver.global.constant.KeyPrefix;
import com.gnimty.communityapiserver.global.constant.Lane;
import com.gnimty.communityapiserver.global.constant.Provider;
import com.gnimty.communityapiserver.global.exception.BaseException;
import com.gnimty.communityapiserver.global.exception.ErrorCode;
import com.gnimty.communityapiserver.global.utils.RandomCodeGenerator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

	private final RiotOauthUtil riotOauthUtil;
	private final RiotAccountReadService riotAccountReadService;
	private final RiotAccountRepository riotAccountRepository;
	private final KakaoOauthUtil kakaoOauthUtil;
	private final GoogleOauthUtil googleOauthUtil;
	private final OauthInfoRepository oauthInfoRepository;
	private final OauthInfoReadService oauthInfoReadService;
	private final IntroductionReadService introductionReadService;
	private final ScheduleReadService scheduleReadService;
	private final PreferGameModeReadService preferGameModeReadService;
	private final IntroductionRepository introductionRepository;
	private final MemberRepository memberRepository;
	private final StringRedisTemplate redisTemplate;
	private final JavaMailSender mailSender;
	private final PasswordEncoder passwordEncoder;

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
			.frequentLane1(Lane.TOP)
			.frequentLane2(Lane.BOTTOM)
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
		throwIfAlreadyLinkedProvider(member, provider);
		String userEmail;
		if (provider.equals(Provider.KAKAO)) {
			userEmail = kakaoOauthUtil.getKakaoUserEmail(request.getAuthCode());
		} else {
			userEmail = googleOauthUtil.getGoogleUserEmail(request.getAuthCode());
		}
		oauthInfoReadService.throwIfExistsByEmailAndProvider(userEmail, provider);
		createOauthInfoByEmail(member, userEmail, provider);
	}

	public MyProfileServiceResponse getMyProfile() {
		final Member member = MemberThreadLocal.get();
		RiotDependentInfo riotDependentInfo = getRiotDependentInfo(member);
		List<OauthInfo> oauthInfos = oauthInfoReadService.findByMember(member);
		List<OauthInfoEntry> oauthInfoEntries = oauthInfos.stream()
			.map(OauthInfoEntry::from)
			.toList();

		return MyProfileServiceResponse.builder()
			.id(member.getId())
			.email(member.getEmail())
			.nickname(member.getNickname())
			.favoriteChampionId(member.getFavoriteChampionID())
			.upCount(member.getUpCount())
			.riotDependentInfo(riotDependentInfo)
			.oauthInfos(oauthInfoEntries)
			.build();
	}

	public void updateMyProfile(MyProfileUpdateServiceRequest request) {
		Member member = MemberThreadLocal.get();
		if (!member.getRsoLinked()) {
			throw new BaseException(ErrorCode.NOT_LINKED_RSO);
		}

		updateMainRiotAccount(request, member);
		updateStatus(request, member);
		updateIntroductions(request, member);
		memberRepository.save(member);
	}

	@Async("mailExecutor")
	public void sendEmailAuthCode(Member member) {
		if (member.getEmail() == null) {
			throw new BaseException(ErrorCode.NOT_LOGIN_BY_FORM);
		}
		String subject = "인증 코드 전송 메일";
		String code = RandomCodeGenerator.generateCodeByLength(6);
		sendMail(member.getEmail(), subject, code);

		ValueOperations<String, String> stringValueOperations = redisTemplate.opsForValue();
		String key = getRedisKey(KeyPrefix.PASSWORD, String.valueOf(member.getId()));
		stringValueOperations.set(key, code);
		stringValueOperations.getAndExpire(
			key,
			Auth.EMAIL_CODE_EXPIRATION.getExpiration(),
			TimeUnit.MILLISECONDS
		);
	}

	public void verifyEmailAuthCode(PasswordEmailVerifyServiceRequest request) {
		Member member = MemberThreadLocal.get();
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

		String emailAuthKey = getRedisKey(KeyPrefix.PASSWORD, String.valueOf(member.getId()));
		String savedCode = valueOperations.get(emailAuthKey);

		if (!request.getCode().equals(savedCode)) {
			throw new BaseException(ErrorCode.INVALID_EMAIL_AUTH_CODE);
		}
		String passwordKey = getRedisKey(KeyPrefix.UPDATE_PASSWORD, String.valueOf(member.getId()));
		saveInRedis(passwordKey, "verified", Auth.PASSWORD_EXPIRATION.getExpiration());
		valueOperations.getAndDelete(emailAuthKey);
	}

	public void updatePassword(PasswordUpdateServiceRequest request) {
		Member member = MemberThreadLocal.get();
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		String verify = valueOperations.get(
			getRedisKey(KeyPrefix.SIGNUP, String.valueOf(member.getId())));
		if (verify == null) {
			throw new BaseException(ErrorCode.UNAUTHORIZED_EMAIL);
		}
		member.updatePassword(passwordEncoder.encode(request.getPassword()));
		memberRepository.save(member);

		redisTemplate.delete(
			getRedisKey(KeyPrefix.UPDATE_PASSWORD, String.valueOf(member.getId())));
	}

	private void sendMail(String to, String subject, String body) {
		MimeMessagePreparator messagePreparator =
			mimeMessage -> {
				final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				helper.setTo(to);
				helper.setSubject(subject);
				helper.setText(body, true);
			};
		mailSender.send(messagePreparator);
	}

	private void updateIntroductions(MyProfileUpdateServiceRequest request, Member member) {
		if (request.getIntroductions() != null) {
			List<IntroductionEntry> updateIntroductions = request.getIntroductions().stream()
				.filter(entry -> entry.getId() != null)
				.peek(entry -> validatePermission(entry.getId(), member))
				.peek(this::updateIntroduction)
				.toList();
			List<IntroductionEntry> insertIntroductions = request.getIntroductions().stream()
				.filter(introductionEntry -> introductionEntry.getId() == null)
				.toList();

			throwIfDuplicatedId(updateIntroductions);
			throwIfDuplicatedIsMain(request);
			List<Introduction> existIntroductions = introductionReadService.findByMember(member);
			throwIfExceedIntroductionCount(existIntroductions.size(), insertIntroductions.size());
			insertIntroductions.forEach(entry -> saveIntroduction(member, entry));
			introductionReadService.throwIfNotExistsMain(member);
		}
	}

	private void saveIntroduction(Member member, IntroductionEntry entry) {
		introductionRepository.save(Introduction.builder()
			.member(member)
			.content(entry.getContent())
			.isMain(entry.getIsMain())
			.build());
	}

	private void updateIntroduction(IntroductionEntry entry) {
		Introduction findIntroduction = introductionReadService.findById(entry.getId());
		findIntroduction.updateContent(entry.getContent());
		findIntroduction.updateIsMain(entry.getIsMain());
	}

	private void validatePermission(Long id, Member member) {
		Introduction findIntroduction = introductionReadService.findById(id);
		if (!Objects.equals(member.getId(), findIntroduction.getMember().getId())) {
			throw new BaseException(ErrorCode.NO_PERMISSION);
		}
	}

	private void throwIfExceedIntroductionCount(int updateSize, int insertSize) {
		if (updateSize + insertSize > 3) {
			throw new BaseException(ErrorCode.EXCEED_INTRODUCTION_COUNT);
		}
	}

	private void throwIfDuplicatedIsMain(MyProfileUpdateServiceRequest request) {
		long mainCount = request.getIntroductions().stream()
			.filter(IntroductionEntry::getIsMain)
			.count();
		if (mainCount > 1) {
			throw new BaseException(ErrorCode.MAIN_CONTENT_MUST_BE_ONLY);
		}
	}

	private void throwIfDuplicatedId(List<IntroductionEntry> introductions) {
		long count = introductions.stream()
			.map(IntroductionEntry::getId)
			.distinct()
			.count();
		if (count != introductions.size()) {
			throw new BaseException(ErrorCode.DUPLICATED_ID);
		}
	}

	private void updateStatus(MyProfileUpdateServiceRequest request, Member member) {
		if (request.getStatus() != null) {
			member.updateStatus(request.getStatus());
		}
	}

	private void updateMainRiotAccount(MyProfileUpdateServiceRequest request, Member member) {
		if (request.getMainRiotAccountId() != null) {
			RiotAccount prevMainAccount = riotAccountReadService.findMainAccountByMember(
				member);
			RiotAccount postMainAccount = riotAccountReadService.findById(
				request.getMainRiotAccountId());
			if (!Objects.equals(postMainAccount.getMember().getId(), member.getId())) {
				throw new BaseException(ErrorCode.NO_PERMISSION);
			}
			prevMainAccount.updateIsMain();
			postMainAccount.updateIsMain();
		}
	}

	private RiotDependentInfo getRiotDependentInfo(Member member) {
		List<RiotAccount> riotAccounts = riotAccountReadService.findByMember(member);
		List<Introduction> introductions = introductionReadService.findByMember(member);
		List<Schedule> schedules = scheduleReadService.findByMember(member);
		List<PreferGameMode> preferGameModes = preferGameModeReadService.findByMember(member);
		return RiotDependentInfo.builder()
			.isLinked(!riotAccounts.isEmpty())
			.status(member.getStatus())
			.introductions(introductions.stream().map(IntroductionEntry::from).toList())
			.schedules(schedules.stream().map(ScheduleEntry::from).toList())
			.preferGameModes(preferGameModes.stream().map(PreferGameModeEntry::from).toList())
			.riotAccounts(riotAccounts.stream().map(RiotAccountEntry::from).toList())
			.build();
	}

	private void createOauthInfoByEmail(Member member, String userEmail, Provider provider) {
		oauthInfoRepository.save(OauthInfo.builder()
			.provider(provider)
			.email(userEmail)
			.member(member)
			.build());
	}

	private String getRedisKey(KeyPrefix prefix, String key) {
		return prefix.getPrefix() + key;
	}

	private void saveInRedis(String key, String value, long timeout) {
		ValueOperations<String, String> stringValueOperations = redisTemplate.opsForValue();

		stringValueOperations.set(key, value);
		stringValueOperations.getAndExpire(
			key,
			timeout,
			TimeUnit.MILLISECONDS
		);
	}

	private void throwIfAlreadyLinkedProvider(Member member, Provider provider) {
		if (oauthInfoReadService.existsByMemberAndProvider(member, provider)) {
			throw new BaseException(ErrorCode.ALREADY_LINKED_OAUTH);
		}
	}
}
