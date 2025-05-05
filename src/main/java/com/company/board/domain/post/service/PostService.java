package com.company.board.domain.post.service;

import com.company.board.domain.post.dto.request.ReqPostPostDto;
import com.company.board.domain.post.dto.response.ResPostGetByIdDto;
import com.company.board.domain.post.dto.response.ResPostGetDto;
import com.company.board.domain.post.dto.response.ResPostPostDto;
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
}