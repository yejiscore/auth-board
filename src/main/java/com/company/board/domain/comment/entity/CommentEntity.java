package com.company.board.domain.comment.entity;

import com.company.board.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "p_comments")
@SQLRestriction("deleted_at IS NULL")
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID commentId;

    @Column(nullable = false)
    private UUID postId;

    @Column(nullable = false)
    private Long commentAuthorId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String commentContent;

    @Builder
    public CommentEntity(UUID postId, Long commentAuthorId, String commentContent) {
        this.postId = postId;
        this.commentAuthorId = commentAuthorId;
        this.commentContent = commentContent;
    }

    public static CommentEntity create(UUID postId, Long commentAuthorId, String commentContent) {
        return CommentEntity.builder()
                .postId(postId)
                .commentAuthorId(commentAuthorId)
                .commentContent(commentContent)
                .build();
    }

    public void update(String newContent) {
        this.commentContent = newContent;
    }
}