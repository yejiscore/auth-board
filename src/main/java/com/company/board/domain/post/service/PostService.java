package com.company.board.domain.post.service;

import com.company.board.domain.post.dto.request.ReqPostPostDto;
import com.company.board.domain.post.dto.request.ReqPostUpdateDto;
import com.company.board.domain.post.dto.response.ResPostGetByIdDto;
import com.company.board.domain.post.dto.response.ResPostGetDto;
import com.company.board.domain.post.dto.response.ResPostPostDto;
import com.company.board.domain.post.dto.response.ResPostUpdateDto;
import com.company.board.domain.post.entity.PostEntity;
import com.company.board.domain.post.repository.PostRepository;
import com.company.board.global.exception.CustomException;
import com.company.board.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final AuditorAware<Long> auditorAware;

    // 생성
    @Transactional
    public ResPostPostDto create(ReqPostPostDto request) {
        Long authorId = auditorAware.getCurrentAuditor().get();

        PostEntity saved = postRepository.save(
                PostEntity.create(
                        authorId,
                        request.getPost().getPostTitle(),
                        request.getPost().getPostContent()
                )
        );

        return ResPostPostDto.from(saved);
    }

    // 전체 조회
    public ResPostGetDto getAll() {
        List<PostEntity> posts = postRepository.findAll();
        return ResPostGetDto.from(posts);
    }

    // 단건 조회
    public ResPostGetByIdDto getById(UUID postId) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        return ResPostGetByIdDto.from(post);
    }

    // 수정
    @Transactional
    public ResPostUpdateDto update(UUID postId, ReqPostUpdateDto request) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

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
            throw new CustomException(ErrorCode.NO_CHANGES_DETECTED);
        }

        return ResPostUpdateDto.from(post);
    }
}