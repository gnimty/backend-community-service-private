package com.gnimty.communityapiserver.domain.member.service.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KakaoLoginServiceRequest {

	private String authCode;
}
