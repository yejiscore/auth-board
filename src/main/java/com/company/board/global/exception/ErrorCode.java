package com.company.board.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 1. 공통
    SUCCESS(HttpStatus.OK, "000", "성공적으로 처리되었습니다."),
    NEED_LOGIN(HttpStatus.UNAUTHORIZED, "001", "로그인이 필요합니다."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "002", "요청값이 유효하지 않습니다."),
    ENUM_TYPE_INVALID(HttpStatus.BAD_REQUEST, "003", "요청한 ENUM 값이 유효하지 않습니다."),
    NO_CHANGES_DETECTED(HttpStatus.BAD_REQUEST, "004", "변경된 내용이 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "999", "서버 내부 오류가 발생했습니다."),

    // 2. Auth
    AUTH_ALREADY_REGISTERED(HttpStatus.BAD_REQUEST, "A100", "이미 사용 중인 닉네임입니다."),
    AUTH_USER_NOT_FOUND(HttpStatus.NOT_FOUND, "A200", "존재하지 않은 회원입니다."),
    AUTH_PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "A300", "비밀번호가 일치하지 않습니다."),
    AUTH_INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "A400", "토큰이 유효하지 않습니다."),

    // 3. User
    USER_ONLY_ADMIN(HttpStatus.FORBIDDEN, "U100", "관리자만 가능한 기능입니다."),
    USER_ONLY_MASTER(HttpStatus.FORBIDDEN, "U200", "마스터만 가능한 기능입니다."),
    USER_ALREADY_WITHDRAWN(HttpStatus.BAD_REQUEST, "U300", "해당 회원은 이미 탈퇴하였습니다."),
    USER_NICKNAME_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "U400", "이미 사용 중인 닉네임입니다."),

    // 4. Post
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "P100", "존재하지 않는 게시글입니다."),
    POST_NOT_AUTHOR(HttpStatus.FORBIDDEN, "P200", "게시글 작성자가 아닙니다."),
    POST_TITLE_EMPTY(HttpStatus.BAD_REQUEST, "P300", "게시글 제목이 비어 있습니다."),
    POST_CONTENT_EMPTY(HttpStatus.BAD_REQUEST, "P400", "게시글 내용이 비어 있습니다."),
    POST_ALREADY_DELETED(HttpStatus.BAD_REQUEST, "P500", "해당 게시글은 이미 삭제되었습니다."),
    POST_FORBIDDEN(HttpStatus.FORBIDDEN, "P600", "해당 게시글을 조회할 권한이 없습니다."),

    // 5. Comment
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "C100", "존재하지 않는 댓글입니다."),
    COMMENT_NOT_AUTHOR(HttpStatus.FORBIDDEN, "C200", "댓글 작성자가 아닙니다."),
    COMMENT_CONTENT_EMPTY(HttpStatus.BAD_REQUEST, "C300", "댓글 내용이 비어 있습니다."),
    COMMENT_ALREADY_DELETED(HttpStatus.BAD_REQUEST, "C400", "해당 댓글은 이미 삭제되었습니다."),
    COMMENT_FORBIDDEN(HttpStatus.FORBIDDEN, "C500", "해당 댓글을 조회할 권한이 없습니다."),
    COMMENT_POST_NOT_FOUND(HttpStatus.NOT_FOUND, "C600", "존재하지 않는 게시글입니다."),
    COMMENT_POST_MISMATCH(HttpStatus.BAD_REQUEST, "C700", "요청한 게시글에 속한 댓글이 아닙니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}