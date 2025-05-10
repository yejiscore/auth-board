package com.company.board.domain.auth.exception;

import com.company.board.global.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements BaseErrorCode {

    AUTH_ALREADY_REGISTERED(HttpStatus.BAD_REQUEST, "A100", "이미 사용 중인 닉네임입니다."),
    AUTH_USER_NOT_FOUND(HttpStatus.NOT_FOUND, "A200", "존재하지 않은 회원입니다."),
    AUTH_PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "A300", "비밀번호가 일치하지 않습니다."),
    AUTH_INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "A400", "토큰이 유효하지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}