package com.company.board.domain.user.service;

import com.company.board.domain.auth.entity.AuthEntity;
import com.company.board.domain.auth.repository.AuthRepository;
import com.company.board.domain.user.dto.response.ResUserGetByIdDto;
import com.company.board.domain.user.dto.response.ResUserGetDto;
import com.company.board.global.exception.CustomException;
import com.company.board.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final AuthRepository authRepository;

    public ResUserGetDto getAll() {
        List<AuthEntity> users = authRepository.findAll();
        return ResUserGetDto.from(users);
    }

    public ResUserGetByIdDto getById(Long userId) {
        AuthEntity user = authRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.AUTH_USER_NOT_FOUND));
        return ResUserGetByIdDto.from(user);
    }
}