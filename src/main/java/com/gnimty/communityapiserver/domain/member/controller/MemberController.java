package com.gnimty.communityapiserver.domain.member.controller;

import com.gnimty.communityapiserver.domain.member.controller.dto.request.LoginRequest;
import com.gnimty.communityapiserver.domain.member.controller.dto.request.SignupRequest;
import com.gnimty.communityapiserver.domain.member.controller.dto.response.AuthToken;
import com.gnimty.communityapiserver.domain.member.service.MemberService;
import com.gnimty.communityapiserver.global.response.CommonResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class MemberController {

	private final MemberService memberService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/auth/signup")
	public CommonResponse<Void> signup(@RequestBody @Valid SignupRequest request) {
		memberService.signup(request.toServiceRequest());
		return CommonResponse.success();
	}

	@PostMapping("/auth/login")
	public CommonResponse<AuthToken> login(@RequestBody @Valid LoginRequest request) {
		return CommonResponse.success(memberService.login(request.toServiceRequest()));
	}
}
