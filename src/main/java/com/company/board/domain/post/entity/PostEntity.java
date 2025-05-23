package com.company.board.domain.post.entity;

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
@Table(name = "p_posts")
@SQLRestriction("deleted_at IS NULL")
public class PostEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID postId;

    @Column(nullable = false)
    private Long postAuthorId;

    @Column(nullable = false, length = 100)
    private String postTitle;

    @Column(nullable = false)
    private String postContent;

    @Builder
    public PostEntity(Long postAuthorId, String postTitle, String postContent) {
        this.postAuthorId = postAuthorId;
        this.postTitle = postTitle;
        this.postContent = postContent;
    }

    public static PostEntity create(Long postAuthorId, String postTitle, String postContent) {
        return PostEntity.builder()
                .postAuthorId(postAuthorId)
                .postTitle(postTitle)
                .postContent(postContent)
                .build();
    }

    public void updateTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public void updateContent(String postContent) {
        this.postContent = postContent;
    }
}
