package com.gnimty.communityapiserver.domain.member.controller.dto.request;

import com.gnimty.communityapiserver.domain.member.service.dto.request.KakaoLoginServiceRequest;
import com.gnimty.communityapiserver.global.exception.ErrorCode.ErrorMessage;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class KakaoLoginRequest {

	@NotNull(message = ErrorMessage.INVALID_INPUT_VALUE)
	private String authCode;

	public KakaoLoginServiceRequest toServiceRequest() {
		return KakaoLoginServiceRequest.builder()
			.authCode(authCode)
			.build();
	}
}
