package com.company.board.domain.post.service;

import com.company.board.domain.post.dto.request.ReqPostPostDto;
import com.company.board.domain.post.dto.response.ResPostPostDto;
import com.company.board.domain.post.entity.PostEntity;
import com.company.board.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final AuditorAware<Long> auditorAware;

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

        return ResPostPostDto.fromPost(saved);
    }
}