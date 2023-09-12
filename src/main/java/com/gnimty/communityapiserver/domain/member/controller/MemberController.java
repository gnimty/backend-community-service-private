package com.gnimty.communityapiserver.domain.member.controller;

import static com.gnimty.communityapiserver.global.constant.ResponseMessage.SUCCESS_GOOGLE_LINK;
import static com.gnimty.communityapiserver.global.constant.ResponseMessage.SUCCESS_KAKAO_LINK;
import static com.gnimty.communityapiserver.global.constant.ResponseMessage.SUCCESS_SUMMONER_LINK;
import static com.gnimty.communityapiserver.global.constant.ResponseMessage.SUCCESS_UPDATE_PROFILE;
import static org.springframework.http.HttpStatus.OK;

import com.gnimty.communityapiserver.domain.member.controller.dto.request.MyProfileUpdateRequest;
import com.gnimty.communityapiserver.domain.member.controller.dto.request.OauthLoginRequest;
import com.gnimty.communityapiserver.domain.member.controller.dto.response.MyProfileResponse;
import com.gnimty.communityapiserver.domain.member.service.MemberService;
import com.gnimty.communityapiserver.domain.member.service.dto.response.MyProfileServiceResponse;
import com.gnimty.communityapiserver.global.constant.Provider;
import com.gnimty.communityapiserver.global.response.CommonResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

	private final MemberService memberService;

	@RequestMapping("/{member_id}/rso")
	public CommonResponse<Void> summonerAccountLink(
		@RequestBody @Valid OauthLoginRequest request
	) {
		memberService.summonerAccountLink(request.toServiceRequest());
		return CommonResponse.success(SUCCESS_SUMMONER_LINK, OK);
	}

	@RequestMapping("/{member_id}/oauth/kakao")
	public CommonResponse<Void> kakaoAdditionalLink(
		@RequestBody @Valid OauthLoginRequest request
	) {
		memberService.oauthAdditionalLink(Provider.KAKAO, request.toServiceRequest());
		return CommonResponse.success(SUCCESS_KAKAO_LINK, OK);
	}

	@RequestMapping("/{member_id}/oauth/google")
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
		System.out.println(request.getStatus());
		memberService.updateMyProfile(request.toServiceRequest());
		return CommonResponse.success(SUCCESS_UPDATE_PROFILE, OK);
	}
}
