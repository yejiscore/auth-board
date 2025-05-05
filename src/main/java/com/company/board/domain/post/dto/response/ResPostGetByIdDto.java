package com.company.board.domain.post.dto.response;

import com.company.board.domain.comment.entity.CommentEntity;
import com.company.board.domain.post.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Builder
public class ResPostGetByIdDto {

    private Post post;

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

    public static ResPostGetByIdDto from(PostEntity postEntity, List<CommentEntity> commentEntities) {
        List<Comment> commentList = commentEntities.stream()
                .map(c -> Comment.builder()
                        .commentId(c.getCommentId())
                        .commentAuthorId(c.getCommentAuthorId())
                        .commentContent(c.getCommentContent())
                        .build())
                .toList();

        return ResPostGetByIdDto.builder()
                .post(Post.builder()
                        .postId(postEntity.getPostId())
                        .postAuthorId(postEntity.getPostAuthorId())
                        .postTitle(postEntity.getPostTitle())
                        .postContent(postEntity.getPostContent())
                        .comments(commentList)
                        .build())
                .build();
    }
}