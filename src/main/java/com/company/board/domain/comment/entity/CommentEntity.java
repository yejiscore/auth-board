package com.company.board.domain.comment.entity;

import com.company.board.domain.post.entity.PostEntity;
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
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    @Column(nullable = false)
    private Long commentAuthorId;

    @Column(nullable = false)
    private String commentContent;

    @Builder
    public CommentEntity(PostEntity post, Long commentAuthorId, String commentContent) {
        this.post = post;
        this.commentAuthorId = commentAuthorId;
        this.commentContent = commentContent;
    }

    public static CommentEntity create(PostEntity post, Long commentAuthorId, String commentContent) {
        return CommentEntity.builder()
                .post(post)
                .commentAuthorId(commentAuthorId)
                .commentContent(commentContent)
                .build();
    }
}