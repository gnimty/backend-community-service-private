package com.gnimty.communityapiserver.global.exception;

import static com.gnimty.communityapiserver.global.exception.ErrorCode.ErrorMessage.INVALID_INPUT_VALUE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.HttpStatus.UNSUPPORTED_MEDIA_TYPE;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum ErrorCode {

	// client
	CONSTRAINT_VIOLATION(BAD_REQUEST, INVALID_INPUT_VALUE),
	URL_NOT_FOUND(NOT_FOUND, "%s : 해당 경로는 존재하지 않는 경로입니다."),
	METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "%s : 해당 HTTP 메소드는 지원되지 않습니다. 허용 메소드 : %s"),
	MEDIA_TYPE_NOT_SUPPORTED(UNSUPPORTED_MEDIA_TYPE, "%s : 지원하지 않는 media type 입니다. 지원 type : %s"),
	ALREADY_REGISTERED_EMAIL(CONFLICT, ErrorMessage.ALREADY_REGISTERED_EMAIL),
	MEMBER_NOT_FOUND(NOT_FOUND, ErrorMessage.MEMBER_NOT_FOUND),
	TOKEN_EXPIRED(UNAUTHORIZED, ErrorMessage.TOKEN_EXPIRED),
	TOKEN_INVALID(UNAUTHORIZED, ErrorMessage.TOKEN_INVALID),
	INVALID_LOGIN(BAD_REQUEST, ErrorMessage.INVALID_LOGIN),
	TOKEN_NOT_FOUND(NOT_FOUND, ErrorMessage.TOKEN_NOT_FOUND),
	NO_PERMISSION(FORBIDDEN, ErrorMessage.NO_PERMISSION),
	ALREADY_LINKED_SUMMONER(CONFLICT, ErrorMessage.ALREADY_LINKED_SUMMONER),

	// server
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Server Error");

	private final HttpStatus status;
	private final String message;

	ErrorCode(HttpStatus httpStatus, String message) {
		this.status = httpStatus;
		this.message = message;
	}

	public static class ErrorMessage {

		private ErrorMessage() {
		}

		public static final String INVALID_INPUT_VALUE = "입력값 중 검증에 실패한 값이 있습니다.";
		public static final String AGREE_TERMS_MUST_BE_TRUE = "약관 동의 항목은 반드시 true여야 합니다.";
		public static final String MEMBER_NOT_FOUND = "token에 해당하는 회원을 찾을 수 없습니다.";
		public static final String TOKEN_EXPIRED = "만료된 token 입니다.";
		public static final String TOKEN_INVALID = "유효하지 않은 token 입니다.";
		public static final String INVALID_LOGIN = "올바르지 않은 로그인 정보입니다.";
		public static final String TOKEN_NOT_FOUND = "토큰이 존재하지 않습니다.";
		public static final String NO_PERMISSION = "해당 자원에 대한 접근 권한이 없습니다.";
		public static final String ALREADY_REGISTERED_EMAIL = "이미 가입된 이메일 입니다.";
		public static final String ALREADY_LINKED_SUMMONER = "이미 연결된 소환사 입니다.";
	}
}