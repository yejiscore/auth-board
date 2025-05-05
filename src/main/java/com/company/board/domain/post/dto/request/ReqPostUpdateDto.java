package com.company.board.domain.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReqPostUpdateDto {

    private Post post;

    @Getter
    @Builder
    public static class Post {
        @NotBlank(message = "제목을 입력해주세요.")
        private String postTitle;

        @NotBlank(message = "내용을 입력해주세요.")
        private String postContent;
    }
}