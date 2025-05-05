package com.company.board.domain.post.controller;

import com.company.board.domain.post.dto.request.ReqPostPostDto;
import com.company.board.domain.post.dto.response.ResPostPostDto;
import com.company.board.domain.post.service.PostService;
import com.company.board.global.dto.CommonResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<CommonResponseDto<ResPostPostDto>> createPost(
            @RequestBody @Valid ReqPostPostDto request
    ) {
        ResPostPostDto response = postService.create(request);
        return ResponseEntity.ok(CommonResponseDto.success(response));
    }
}