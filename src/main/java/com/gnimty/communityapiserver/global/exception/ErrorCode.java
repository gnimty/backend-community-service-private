package com.gnimty.communityapiserver.global.exception;

import static com.gnimty.communityapiserver.global.exception.ErrorCode.ErrorMessage.INVALID_INPUT_VALUE;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
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
	ALREADY_REGISTERED_EMAIL(CONFLICT, "이미 인증된 이메일 입니다."),

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
	}
}