package com.company.board.domain.auth.service;

import com.company.board.domain.auth.dto.request.ReqAuthSigninPostDto;
import com.company.board.domain.auth.dto.request.ReqAuthSignupPostDto;
import com.company.board.domain.auth.dto.response.ResAuthSigninPostDto;
import com.company.board.domain.auth.dto.response.ResAuthSignupPostDto;
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
    public ResAuthSignupPostDto signUp(ReqAuthSignupPostDto request) {
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

        return ResAuthSignupPostDto.from(
                saved.getUserId(),
                saved.getLoginId(),
                saved.getRole()
        );
    }

    public ResAuthSigninPostDto signIn(ReqAuthSigninPostDto request) {
        AuthEntity auth = authRepository.findByLoginId(request.getLoginId());

        if (auth == null) {
            throw new CustomException(ErrorCode.AUTH_USER_NOT_FOUND);
        }

        boolean matches = auth.matchPassword(request.getPassword(), passwordEncryptionService);
        if (!matches) {
            throw new CustomException(ErrorCode.AUTH_PASSWORD_MISMATCH);
        }

        return ResAuthSigninPostDto.from(auth.getUserId(), auth.getLoginId(), auth.getRole());
    }
}
