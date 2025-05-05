package com.company.board.domain.comment.dto.response;

import com.company.board.domain.comment.entity.CommentEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class ResCommentPostDto {

    private Comment comment;

    @Getter
    @Builder
    public static class Comment {
        private UUID commentId;
        private UUID postId;
        private Long commentAuthorId;
        private String commentContent;
    }

    public static ResCommentPostDto from(CommentEntity commentEntity) {
        return ResCommentPostDto.builder()
                .comment(Comment.builder()
                        .commentId(commentEntity.getCommentId())
                        .postId(commentEntity.getPost().getPostId())
                        .commentAuthorId(commentEntity.getCommentAuthorId())
                        .commentContent(commentEntity.getCommentContent())
                        .build())
                .build();
    }
}