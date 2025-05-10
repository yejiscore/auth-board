package com.company.board.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CommonErrorCode implements BaseErrorCode {

    SUCCESS(HttpStatus.OK, "000", "성공적으로 처리되었습니다."),
    NEED_LOGIN(HttpStatus.UNAUTHORIZED, "001", "로그인이 필요합니다."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "002", "요청값이 유효하지 않습니다."),
    ENUM_TYPE_INVALID(HttpStatus.BAD_REQUEST, "003", "요청한 ENUM 값이 유효하지 않습니다."),
    NO_CHANGES_DETECTED(HttpStatus.BAD_REQUEST, "004", "변경된 내용이 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "999", "서버 내부 오류가 발생했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}