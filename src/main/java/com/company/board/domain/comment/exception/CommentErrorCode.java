package com.company.board.domain.comment.exception;

import com.company.board.global.exception.BaseErrorCode;
import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommentErrorCode implements BaseErrorCode {

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