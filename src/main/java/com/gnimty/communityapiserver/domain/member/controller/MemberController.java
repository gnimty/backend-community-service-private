package com.gnimty.communityapiserver.domain.member.controller;

import com.gnimty.communityapiserver.domain.member.controller.dto.request.OauthLoginRequest;
import com.gnimty.communityapiserver.domain.member.service.MemberService;
import com.gnimty.communityapiserver.global.constant.Provider;
import com.gnimty.communityapiserver.global.response.CommonResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
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
		return CommonResponse.success();
	}

	@RequestMapping("/{member_id}/oauth/kakao")
	public CommonResponse<Void> kakaoAdditionalLink(
		@RequestBody @Valid OauthLoginRequest request
	) {
		memberService.oauthAdditionalLink(Provider.KAKAO, request.toServiceRequest());
		return CommonResponse.success();
	}

	@RequestMapping("/{member_id}/oauth/google")
	public CommonResponse<Void> googleAdditionalLink(
		@RequestBody @Valid OauthLoginRequest request
	) {
		memberService.oauthAdditionalLink(Provider.GOOGLE, request.toServiceRequest());
		return CommonResponse.success();
	}
}
