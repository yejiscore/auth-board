package com.company.board.domain.auth.controller;

import com.company.board.domain.auth.dto.request.ReqAuthSigninPostDto;
import com.company.board.domain.auth.dto.request.ReqAuthSignupPostDto;
import com.company.board.domain.auth.dto.response.ResAuthSigninPostDto;
import com.company.board.domain.auth.dto.response.ResAuthSignupPostDto;
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
    public ResponseEntity<CommonResponseDto<ResAuthSignupPostDto>> signUp(
            @Valid @RequestBody ReqAuthSignupPostDto request
    ) {
        ResAuthSignupPostDto response = authService.signUp(request);
        return ResponseEntity.ok(CommonResponseDto.success(response));
    }

    @PostMapping("/signin")
    public ResponseEntity<CommonResponseDto<ResAuthSigninPostDto>> signin(
            @Valid @RequestBody ReqAuthSigninPostDto request) {
        ResAuthSigninPostDto response = authService.signIn(request);
        return ResponseEntity.ok(CommonResponseDto.success(response));
    }
}