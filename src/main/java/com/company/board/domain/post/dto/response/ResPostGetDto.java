package com.company.board.domain.post.dto.response;

import com.company.board.domain.post.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Builder
public class ResPostGetDto {

    private List<Post> posts;

    @Getter
    @Builder
    public static class Post {
        private UUID postId;
        private Long postAuthorId;
        private String postTitle;
        private String postContent;
    }

    public static ResPostGetDto from(List<PostEntity> postEntities) {
        List<Post> postList = postEntities.stream()
                .map(postEntity -> Post.builder()
                        .postId(postEntity.getPostId())
                        .postAuthorId(postEntity.getPostAuthorId())
                        .postTitle(postEntity.getPostTitle())
                        .postContent(postEntity.getPostContent())
                        .build())
                .collect(Collectors.toList());

        return ResPostGetDto.builder()
                .posts(postList)
                .build();
    }
}