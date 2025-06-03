package com.company.board.domain.auth.service;

import com.company.board.domain.auth.dto.request.ReqAuthSigninPostDto;
import com.company.board.domain.auth.dto.request.ReqAuthSignupPostDto;
import com.company.board.domain.auth.dto.response.ResAuthSigninPostDto;
import com.company.board.domain.auth.dto.response.ResAuthSignupPostDto;
import com.company.board.domain.user.entity.UserEntity;
import com.company.board.domain.auth.repository.AuthRepository;
import com.company.board.global.exception.CustomException;
import com.company.board.domain.auth.exception.AuthErrorCode;
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
        if (authRepository.existsByNickname(request.getUser().getNickname())) {
            throw new CustomException(AuthErrorCode.AUTH_ALREADY_REGISTERED);
        }

        String encodedPassword = passwordEncryptionService.encode(request.getUser().getPassword());

        UserEntity saved = authRepository.save(
                UserEntity.create(
                        request.getUser().getNickname(),
                        encodedPassword,
                        request.getUser().getRole()
                )
        );

        return ResAuthSignupPostDto.from(saved);
    }

    public ResAuthSigninPostDto signIn(ReqAuthSigninPostDto request) {
        UserEntity auth = authRepository.findByNickname(request.getUser().getNickname());

        if (auth == null) {
            throw new CustomException(AuthErrorCode.AUTH_USER_NOT_FOUND);
        }

        if (!auth.matchPassword(request.getUser().getPassword(), passwordEncryptionService)) {
            throw new CustomException(AuthErrorCode.AUTH_PASSWORD_MISMATCH);
        }

        return ResAuthSigninPostDto.from(auth);
    }
}