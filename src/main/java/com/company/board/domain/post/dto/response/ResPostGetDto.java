package com.company.board.domain.post.dto.response;

import com.company.board.domain.comment.entity.CommentEntity;
import com.company.board.domain.post.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;
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
        private List<Comment> comments;
    }

    @Getter
    @Builder
    public static class Comment {
        private UUID commentId;
        private Long commentAuthorId;
        private String commentContent;
    }

    public static ResPostGetDto from(List<PostEntity> postEntities, Map<UUID, List<CommentEntity>> commentMap) {
        List<Post> postList = postEntities.stream()
                .map(post -> Post.builder()
                        .postId(post.getPostId())
                        .postAuthorId(post.getPostAuthorId())
                        .postTitle(post.getPostTitle())
                        .postContent(post.getPostContent())
                        .comments(commentMap.getOrDefault(post.getPostId(), List.of()).stream()
                                .map(c -> Comment.builder()
                                        .commentId(c.getCommentId())
                                        .commentAuthorId(c.getCommentAuthorId())
                                        .commentContent(c.getCommentContent())
                                        .build())
                                .toList())
                        .build())
                .toList();

        return ResPostGetDto.builder()
                .posts(postList)
                .build();
    }
}