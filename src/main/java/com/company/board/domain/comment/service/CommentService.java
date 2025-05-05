package com.company.board.domain.comment.service;

import com.company.board.domain.comment.dto.request.ReqCommentPostDto;
import com.company.board.domain.comment.dto.response.ResCommentPostDto;
import com.company.board.domain.comment.entity.CommentEntity;
import com.company.board.domain.comment.repository.CommentRepository;
import com.company.board.domain.post.entity.PostEntity;
import com.company.board.domain.post.repository.PostRepository;
import com.company.board.global.config.AuditorAwareImpl;
import com.company.board.global.exception.CustomException;
import com.company.board.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final AuditorAwareImpl auditorAware;

    @Transactional
    public ResCommentPostDto create(UUID postId, ReqCommentPostDto request) {
        String content = request.getComment().getCommentContent();

        if (content == null || content.trim().isEmpty()) {
            throw new CustomException(ErrorCode.COMMENT_CONTENT_EMPTY);
        }

        Long authorId = auditorAware.getCurrentAuditor().get();

        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        CommentEntity saved = commentRepository.save(
                CommentEntity.create(post, authorId, content)
        );

        return ResCommentPostDto.from(saved);
    }
}