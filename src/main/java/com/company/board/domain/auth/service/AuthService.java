package com.company.board.domain.auth.service;

import com.company.board.domain.auth.dto.request.ReqAuthPostDto;
import com.company.board.domain.auth.dto.response.ResAuthPostDto;
import com.company.board.domain.auth.entity.AuthEntity;
import com.company.board.domain.auth.repository.AuthRepository;
import com.company.board.global.exception.CustomException;
import com.company.board.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncryptionService passwordEncryptionService;

    @Transactional
    public ResAuthPostDto signUp(ReqAuthPostDto request) {
        if (authRepository.existsByLoginId(request.getLoginId())) {
            throw new CustomException(ErrorCode.AUTH_ALREADY_REGISTERED);
        }

        String encodedPassword = passwordEncryptionService.encode(request.getPassword());

        AuthEntity saved = authRepository.save(
                AuthEntity.create(
                        request.getLoginId(),
                        encodedPassword,
                        request.getRole()
                )
        );

        return ResAuthPostDto.from(
                saved.getUserId(),
                saved.getLoginId(),
                saved.getRole()
        );
    }
}
