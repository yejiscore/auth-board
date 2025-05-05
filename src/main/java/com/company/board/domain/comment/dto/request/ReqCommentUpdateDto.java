package com.company.board.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReqCommentUpdateDto {

    private Comment comment;

    @Getter
    @Builder
    public static class Comment {
        @NotBlank(message = "내용을 입력해주세요.")
        private String commentContent;
    }
}
