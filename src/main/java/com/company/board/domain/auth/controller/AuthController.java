package com.company.board.domain.auth.controller;

import com.company.board.domain.auth.dto.request.ReqAuthPostDto;
import com.company.board.domain.auth.dto.response.ResAuthPostDto;
import com.company.board.domain.auth.service.AuthService;
import com.company.board.global.dto.CommonResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto<ResAuthPostDto>> signUp(
            @Valid @RequestBody ReqAuthPostDto request
    ) {
        ResAuthPostDto response = authService.signUp(request);
        return ResponseEntity.ok(CommonResponseDto.success(response));
    }
}