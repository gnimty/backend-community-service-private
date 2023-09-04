package com.gnimty.communityapiserver.domain.member.controller;

import com.gnimty.communityapiserver.domain.member.controller.dto.request.EmailAuthRequest;
import com.gnimty.communityapiserver.domain.member.controller.dto.request.OauthLoginRequest;
import com.gnimty.communityapiserver.domain.member.controller.dto.request.LoginRequest;
import com.gnimty.communityapiserver.domain.member.controller.dto.request.SignupRequest;
import com.gnimty.communityapiserver.domain.member.controller.dto.response.AuthToken;
import com.gnimty.communityapiserver.domain.member.service.AuthService;
import com.gnimty.communityapiserver.global.response.CommonResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/auth/signup")
	public CommonResponse<Void> signup(@RequestBody @Valid SignupRequest request) {
		authService.signup(request.toServiceRequest());
		return CommonResponse.success();
	}

	@PostMapping("/auth/login")
	public CommonResponse<AuthToken> login(@RequestBody @Valid LoginRequest request) {
		return CommonResponse.success(authService.login(request.toServiceRequest()));
	}

	@PostMapping("/oauth/kakao")
	public CommonResponse<AuthToken> kakaoLogin(@RequestBody @Valid OauthLoginRequest request) {
		return CommonResponse.success(authService.kakaoLogin(request.toServiceRequest()));
	}

	@PostMapping("/oauth/google")
	public CommonResponse<AuthToken> googleLogin(@RequestBody @Valid OauthLoginRequest request) {
		return CommonResponse.success(authService.googleLogin(request.toServiceRequest()));
	}

	@PostMapping("/auth/email")
	public CommonResponse<Void> sendEmailAuthCode(@RequestBody @Valid EmailAuthRequest request) {
		authService.sendEmailAuthCode(request.toServiceRequest());
		return CommonResponse.success();
	}
}
