package com.company.board.domain.comment.controller;

import com.company.board.domain.comment.dto.request.ReqCommentPostDto;
import com.company.board.domain.comment.dto.response.ResCommentPostDto;
import com.company.board.domain.comment.service.CommentService;
import com.company.board.global.dto.CommonResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommonResponseDto<ResCommentPostDto>> createComment(
            @PathVariable UUID postId,
            @Valid @RequestBody ReqCommentPostDto request
    ) {
        ResCommentPostDto response = commentService.create(postId, request);
        return ResponseEntity.ok(CommonResponseDto.success(response));
    }
}