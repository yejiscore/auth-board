package com.company.board.domain.comment.dto.response;

import com.company.board.domain.comment.entity.CommentEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class ResCommentGetDto {

    private List<Comment> comments;

    @Getter
    @Builder
    public static class Comment {
        private UUID commentId;
        private Long commentAuthorId;
        private String commentContent;
    }

    public static ResCommentGetDto from(List<CommentEntity> commentEntities) {
        List<Comment> commentList = commentEntities.stream()
                .map(entity -> Comment.builder()
                        .commentId(entity.getCommentId())
                        .commentAuthorId(entity.getCommentAuthorId())
                        .commentContent(entity.getCommentContent())
                        .build())
                .toList();

        return ResCommentGetDto.builder()
                .comments(commentList)
                .build();
    }
}
