package com.company.board.domain.comment.service;

import com.company.board.domain.comment.dto.request.ReqCommentPostDto;
import com.company.board.domain.comment.dto.response.ResCommentGetByIdDto;
import com.company.board.domain.comment.dto.response.ResCommentGetDto;
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

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final AuditorAwareImpl auditorAware;

    // 생성
    @Transactional
    public ResCommentPostDto create(UUID postId, ReqCommentPostDto request) {
        String content = request.getComment().getCommentContent();

        if (content == null || content.trim().isEmpty()) {
            throw new CustomException(ErrorCode.COMMENT_CONTENT_EMPTY);
        }

        Long authorId = auditorAware.getCurrentAuditor().get();

        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_POST_NOT_FOUND));

        CommentEntity saved = commentRepository.save(
                CommentEntity.create(post, authorId, content)
        );

        return ResCommentPostDto.from(saved);
    }

    // 전체 조회
    @Transactional(readOnly = true)
    public ResCommentGetDto getCommentsByPostId(UUID postId) {

        // 존재하는 게시글인지 검증
        postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_POST_NOT_FOUND));

        List<CommentEntity> comments = commentRepository.findAllByPost_PostId(postId);
        return ResCommentGetDto.from(comments);
    }

    // 단건 조회
    @Transactional(readOnly = true)
    public ResCommentGetByIdDto getCommentById(UUID postId, UUID commentId) {

        // 존재하는 게시글인지 검증
        postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_POST_NOT_FOUND));

        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        // 해당 댓글이 요청한 게시글에 속해 있는지 검증
        if (!comment.getPost().getPostId().equals(postId)) {
            throw new CustomException(ErrorCode.COMMENT_POST_MISMATCH);
        }

        return ResCommentGetByIdDto.from(comment);
    }
}