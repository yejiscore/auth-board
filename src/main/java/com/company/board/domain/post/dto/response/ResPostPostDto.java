package com.company.board.domain.post.dto.response;

import com.company.board.domain.post.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class ResPostPostDto {

    private Post post;

    @Getter
    @Builder
    public static class Post {
        private UUID postId;
        private Long postAuthorId;
        private String postTitle;
        private String postContent;
    }

    public static ResPostPostDto fromPost(PostEntity postEntity) {
        return ResPostPostDto.builder()
                .post(Post.builder()
                        .postId(postEntity.getPostId())
                        .postAuthorId(postEntity.getPostAuthorId())
                        .postTitle(postEntity.getPostTitle())
                        .postContent(postEntity.getPostContent())
                        .build())
                .build();
    }
}