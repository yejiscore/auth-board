package com.company.board.domain.post.exception;

import com.company.board.global.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PostErrorCode implements BaseErrorCode {

    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "P100", "존재하지 않는 게시글입니다."),
    POST_NOT_AUTHOR(HttpStatus.FORBIDDEN, "P200", "게시글 작성자가 아닙니다."),
    POST_TITLE_EMPTY(HttpStatus.BAD_REQUEST, "P300", "게시글 제목이 비어 있습니다."),
    POST_CONTENT_EMPTY(HttpStatus.BAD_REQUEST, "P400", "게시글 내용이 비어 있습니다."),
    POST_ALREADY_DELETED(HttpStatus.BAD_REQUEST, "P500", "해당 게시글은 이미 삭제되었습니다."),
    POST_FORBIDDEN(HttpStatus.FORBIDDEN, "P600", "해당 게시글을 조회할 권한이 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}