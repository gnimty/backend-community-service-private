package com.gnimty.communityapiserver.domain.member.controller;

import static com.gnimty.communityapiserver.global.constant.ResponseMessage.SUCCESS_GOOGLE_LINK;
import static com.gnimty.communityapiserver.global.constant.ResponseMessage.SUCCESS_KAKAO_LINK;
import static com.gnimty.communityapiserver.global.constant.ResponseMessage.SUCCESS_SEND_EMAIL_AUTH_CODE;
import static com.gnimty.communityapiserver.global.constant.ResponseMessage.SUCCESS_SUMMONER_LINK;
import static com.gnimty.communityapiserver.global.constant.ResponseMessage.SUCCESS_UPDATE_PASSWORD;
import static com.gnimty.communityapiserver.global.constant.ResponseMessage.SUCCESS_UPDATE_PROFILE;
import static com.gnimty.communityapiserver.global.constant.ResponseMessage.SUCCESS_VERIFY_EMAIL;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.OK;

import com.gnimty.communityapiserver.domain.member.controller.dto.request.MyProfileUpdateRequest;
import com.gnimty.communityapiserver.domain.member.controller.dto.request.OauthLoginRequest;
import com.gnimty.communityapiserver.domain.member.controller.dto.request.PasswordEmailVerifyRequest;
import com.gnimty.communityapiserver.domain.member.controller.dto.request.PasswordUpdateRequest;
import com.gnimty.communityapiserver.domain.member.controller.dto.response.MyProfileResponse;
import com.gnimty.communityapiserver.domain.member.service.MemberService;
import com.gnimty.communityapiserver.domain.member.service.dto.response.MyProfileServiceResponse;
import com.gnimty.communityapiserver.global.auth.MemberThreadLocal;
import com.gnimty.communityapiserver.global.constant.Provider;
import com.gnimty.communityapiserver.global.response.CommonResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/{member_id}/rso")
	public CommonResponse<Void> summonerAccountLink(
		@RequestBody @Valid OauthLoginRequest request
	) {
		memberService.summonerAccountLink(request.toServiceRequest());
		return CommonResponse.success(SUCCESS_SUMMONER_LINK, OK);
	}

	@PostMapping("/{member_id}/oauth/kakao")
	public CommonResponse<Void> kakaoAdditionalLink(
		@RequestBody @Valid OauthLoginRequest request
	) {
		memberService.oauthAdditionalLink(Provider.KAKAO, request.toServiceRequest());
		return CommonResponse.success(SUCCESS_KAKAO_LINK, OK);
	}

	@PostMapping("/{member_id}/oauth/google")
	public CommonResponse<Void> googleAdditionalLink(
		@RequestBody @Valid OauthLoginRequest request
	) {
		memberService.oauthAdditionalLink(Provider.GOOGLE, request.toServiceRequest());
		return CommonResponse.success(SUCCESS_GOOGLE_LINK, OK);
	}

	@GetMapping("/me")
	public CommonResponse<MyProfileResponse> getMyProfile() {
		MyProfileServiceResponse response = memberService.getMyProfile();
		return CommonResponse.success(MyProfileResponse.from(response));
	}

	@PatchMapping("/{member_id}")
	public CommonResponse<Void> updateMyProfile(
		@RequestBody @Valid MyProfileUpdateRequest request
	) {
		memberService.updateMyProfile(request.toServiceRequest());
		return CommonResponse.success(SUCCESS_UPDATE_PROFILE, OK);
	}

	@PostMapping("/{member_id}/password/email")
	public CommonResponse<Void> sendEmailAuthCode() {
		memberService.sendEmailAuthCode(MemberThreadLocal.get());
		return CommonResponse.success(SUCCESS_SEND_EMAIL_AUTH_CODE, ACCEPTED);
	}

	@PostMapping("/{member_id}/password/email/code")
	public CommonResponse<Void> verifyEmailAuthCode(
		@RequestBody @Valid PasswordEmailVerifyRequest request
	) {
		memberService.verifyEmailAuthCode(request.toServiceRequest());
		return CommonResponse.success(SUCCESS_VERIFY_EMAIL, OK);
	}

	@PatchMapping("/{member_id}/password")
	public CommonResponse<Void> updatePassword(
		@RequestBody @Valid PasswordUpdateRequest request
	) {
		memberService.updatePassword(request.toServiceRequest());
		return CommonResponse.success(SUCCESS_UPDATE_PASSWORD, OK);
	}
}
