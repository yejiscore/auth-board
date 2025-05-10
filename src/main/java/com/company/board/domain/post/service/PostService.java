package com.company.board.domain.post.service;

import com.company.board.domain.comment.entity.CommentEntity;
import com.company.board.domain.comment.repository.CommentRepository;
import com.company.board.domain.post.dto.request.ReqPostPostDto;
import com.company.board.domain.post.dto.request.ReqPostUpdateDto;
import com.company.board.domain.post.dto.response.ResPostGetByIdDto;
import com.company.board.domain.post.dto.response.ResPostGetDto;
import com.company.board.domain.post.dto.response.ResPostPostDto;
import com.company.board.domain.post.dto.response.ResPostUpdateDto;
import com.company.board.domain.post.entity.PostEntity;
import com.company.board.domain.post.repository.PostRepository;
import com.company.board.global.exception.CommonErrorCode;
import com.company.board.global.exception.CustomException;
import com.company.board.domain.post.exception.PostErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final AuditorAware<Long> auditorAware;

    // 생성
    @Transactional
    public ResPostPostDto create(ReqPostPostDto request) {
        String title = request.getPost().getPostTitle();
        String content = request.getPost().getPostContent();

        if (title == null || title.trim().isEmpty()) {
            throw new CustomException(PostErrorCode.POST_TITLE_EMPTY);
        }

        if (content == null || content.trim().isEmpty()) {
            throw new CustomException(PostErrorCode.POST_CONTENT_EMPTY);
        }

        Long authorId = auditorAware.getCurrentAuditor().get();

        PostEntity saved = postRepository.save(
                PostEntity.create(authorId, title, content)
        );

        return ResPostPostDto.from(saved);
    }

    // 전체 조회
    public ResPostGetDto getAll() {
        List<PostEntity> posts = postRepository.findAll();
        List<CommentEntity> allComments = commentRepository.findAll();

        Map<UUID, List<CommentEntity>> commentMap = allComments.stream()
                .collect(Collectors.groupingBy(c -> c.getPost().getPostId()));

        return ResPostGetDto.from(posts, commentMap);
    }

    // 단건 조회
    public ResPostGetByIdDto getById(UUID postId) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(PostErrorCode.POST_NOT_FOUND));

        List<CommentEntity> comments = commentRepository.findAllByPost_PostId(post.getPostId());

        return ResPostGetByIdDto.from(post, comments);
    }

    // 수정
    @Transactional
    public ResPostUpdateDto update(UUID postId, ReqPostUpdateDto request) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(PostErrorCode.POST_NOT_FOUND));

        // 변경이 제대로 이루어져있는 확인
        boolean updated = false;

        if (request.getPost().getPostTitle() != null &&
                !request.getPost().getPostTitle().equals(post.getPostTitle())) {
            post.updateTitle(request.getPost().getPostTitle());
            updated = true;
        }

        if (request.getPost().getPostContent() != null &&
                !request.getPost().getPostContent().equals(post.getPostContent())) {
            post.updateContent(request.getPost().getPostContent());
            updated = true;
        }

        // 변경된 값이 없을 때 Error
        if (!updated) {
            throw new CustomException(CommonErrorCode.NO_CHANGES_DETECTED);
        }

        return ResPostUpdateDto.from(post);
    }

    // 삭제
    @Transactional
    public void delete(UUID postId) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(PostErrorCode.POST_NOT_FOUND));

        // 이미 삭제된 경우
        if (post.getDeletedAt() != null) {
            throw new CustomException(PostErrorCode.POST_ALREADY_DELETED);
        }

        Long deletedBy = auditorAware.getCurrentAuditor().get();

        // 해당 게시글에 달린 댓글도 삭제
        List<CommentEntity> comments = commentRepository.findAllByPost_PostId(postId);

        if (!comments.isEmpty()) {
            comments.forEach(comment -> comment.delete(deletedBy));
        }

        post.delete(deletedBy);
    }
}