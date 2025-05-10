package com.company.board.domain.user.exception;

import com.company.board.global.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    USER_ONLY_ADMIN(HttpStatus.FORBIDDEN, "U100", "관리자만 가능한 기능입니다."),
    USER_ONLY_MASTER(HttpStatus.FORBIDDEN, "U200", "마스터만 가능한 기능입니다."),
    USER_ALREADY_WITHDRAWN(HttpStatus.BAD_REQUEST, "U300", "해당 회원은 이미 탈퇴하였습니다."),
    USER_NICKNAME_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "U400", "이미 사용 중인 닉네임입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "001", "존재하지 않는 회원입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}