package com.gnimty.communityapiserver.domain.member.controller;

import com.gnimty.communityapiserver.domain.member.controller.dto.request.SignupRequest;
import com.gnimty.communityapiserver.domain.member.service.MemberService;
import com.gnimty.communityapiserver.global.response.CommonResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/auth/signup")
	public CommonResponse<Void> signup(@RequestBody @Valid SignupRequest request) {
		memberService.signup(request.toServiceRequest());
		return CommonResponse.success();
	}
}
