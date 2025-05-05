package com.company.board.domain.comment.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReqCommentPostDto {

    @Valid
    @NotNull
    private Comment comment;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Comment {
        @NotBlank(message = "내용을 입력해주세요.")
        private String commentContent;
    }
}