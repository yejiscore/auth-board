package com.company.board.domain.comment.controller;

import com.company.board.domain.comment.dto.request.ReqCommentPostDto;
import com.company.board.domain.comment.dto.request.ReqCommentUpdateDto;
import com.company.board.domain.comment.dto.response.ResCommentGetByIdDto;
import com.company.board.domain.comment.dto.response.ResCommentGetDto;
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
    public ResponseEntity<CommonResponseDto<ResCommentPostDto>> create(
            @PathVariable UUID postId,
            @Valid @RequestBody ReqCommentPostDto request
    ) {
        ResCommentPostDto response = commentService.create(postId, request);
        return ResponseEntity.ok(CommonResponseDto.success(response));
    }

    @GetMapping
    public ResponseEntity<CommonResponseDto<ResCommentGetDto>> getAll(
            @PathVariable UUID postId
    ) {
        ResCommentGetDto response = commentService.getAll(postId);
        return ResponseEntity.ok(CommonResponseDto.success(response));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommonResponseDto<ResCommentGetByIdDto>> getById(
            @PathVariable UUID postId,
            @PathVariable UUID commentId
    ) {
        ResCommentGetByIdDto response = commentService.getById(postId, commentId);
        return ResponseEntity.ok(CommonResponseDto.success(response));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommonResponseDto<ResCommentGetByIdDto>> update(
            @PathVariable UUID postId,
            @PathVariable UUID commentId,
            @Valid @RequestBody ReqCommentUpdateDto request
    ) {
        ResCommentGetByIdDto response = commentService.update(postId, commentId, request);
        return ResponseEntity.ok(CommonResponseDto.success(response));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommonResponseDto<Void>> delete(
            @PathVariable UUID postId,
            @PathVariable UUID commentId
    ) {
        commentService.delete(postId, commentId);
        return ResponseEntity.ok(CommonResponseDto.success(null));
    }
}