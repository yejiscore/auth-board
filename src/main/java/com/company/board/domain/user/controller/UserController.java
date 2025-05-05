package com.company.board.domain.user.controller;

import com.company.board.domain.user.dto.response.ResUserGetByIdDto;
import com.company.board.domain.user.dto.response.ResUserGetDto;
import com.company.board.domain.user.service.UserService;
import com.company.board.global.dto.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<CommonResponseDto<ResUserGetDto>> getAll() {
        return ResponseEntity.ok(CommonResponseDto.success(userService.getAll()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CommonResponseDto<ResUserGetByIdDto>> getById(@PathVariable Long userId) {
        return ResponseEntity.ok(CommonResponseDto.success(userService.getById(userId)));
    }
}