package com.company.board.domain.comment.dto.response;

import com.company.board.domain.comment.entity.CommentEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class ResCommentGetByIdDto {

    private Comment comment;

    @Getter
    @Builder
    public static class Comment {
        private UUID commentId;
        private Long commentAuthorId;
        private String commentContent;
    }

    public static ResCommentGetByIdDto from(CommentEntity commentEntity) {
        return ResCommentGetByIdDto.builder()
                .comment(Comment.builder()
                        .commentId(commentEntity.getCommentId())
                        .commentAuthorId(commentEntity.getCommentAuthorId())
                        .commentContent(commentEntity.getCommentContent())
                        .build())
                .build();
    }
}