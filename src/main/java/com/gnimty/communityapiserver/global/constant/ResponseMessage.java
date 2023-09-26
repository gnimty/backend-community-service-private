package com.gnimty.communityapiserver.global.constant;

import lombok.Getter;

@Getter
public enum ResponseMessage {
	SUCCESS_SUMMONER_LINK("성공적으로 소환사 계정 연결을 완료하였습니다."),
	SUCCESS_KAKAO_LINK("성공적으로 카카오 계정 연결을 완료하였습니다."),
	SUCCESS_GOOGLE_LINK("성공적으로 구글 계정 연결을 완료하였습니다."),
	SUCCESS_SIGN_UP("성공적으로 회원가입을 완료하였습니다."),
	SUCCESS_SEND_EMAIL_AUTH_CODE("성공적으로 이메일 인증 코드를 발송하였습니다."),
	SUCCESS_VERIFY_EMAIL("이메일 인증을 성공적으로 완료하였습니다."),
	SUCCESS_UPDATE_PROFILE("성공적으로 프로필 수정을 완료하였습니다."),
	SUCCESS_UPDATE_PASSWORD("성공적으로 비밀번호 변경을 완료하였습니다."),
	SUCCESS_UPDATE_SUMMONERS("성공적으로 소환사 정보를 변경하였습니다."),
	SUCCESS_UPDATE_SCHEDULES("성공적으로 게임 가능 시간를 변경하였습니다."),
	SUCCESS_BLOCK("성공적으로 회원 차단을 완료하였습니다."),
	SUCCESS_CLEAR_BLOCK("성공적으로 차단 해제를 완료하였습니다.");

	private final String message;

	ResponseMessage(String message) {
		this.message = message;
	}
}
