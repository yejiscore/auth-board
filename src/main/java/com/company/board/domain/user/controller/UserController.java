package com.company.board.domain.user.controller;

import com.company.board.domain.user.dto.request.ReqUserUpdateDto;
import com.company.board.domain.user.dto.response.ResUserGetByIdDto;
import com.company.board.domain.user.dto.response.ResUserGetDto;
import com.company.board.domain.user.dto.response.ResUserUpdateDto;
import com.company.board.domain.user.service.UserService;
import com.company.board.global.dto.CommonResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    // 전체 조회
    @GetMapping
    public ResponseEntity<CommonResponseDto<ResUserGetDto>> getAll() {
        return ResponseEntity.ok(CommonResponseDto.success(userService.getAll()));
    }

    // 단건 조회
    @GetMapping("/{userId}")
    public ResponseEntity<CommonResponseDto<ResUserGetByIdDto>> getById(@PathVariable Long userId) {
        return ResponseEntity.ok(CommonResponseDto.success(userService.getById(userId)));
    }

    // 수정
    @PutMapping("/{userId}")
    public ResponseEntity<CommonResponseDto<ResUserUpdateDto>> update(
            @PathVariable Long userId,
            @RequestBody @Valid ReqUserUpdateDto request
    ) {
        ResUserUpdateDto response = userService.update(userId, request);
        return ResponseEntity.ok(CommonResponseDto.success(response));
    }

    // 삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<CommonResponseDto<Void>> delete(@PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.ok(CommonResponseDto.success(null));
    }
}