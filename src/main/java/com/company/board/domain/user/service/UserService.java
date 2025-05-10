package com.company.board.domain.user.service;

import com.company.board.domain.auth.entity.AuthEntity;
import com.company.board.domain.auth.repository.AuthRepository;
import com.company.board.domain.auth.service.PasswordEncryptionService;
import com.company.board.domain.user.dto.request.ReqUserUpdateDto;
import com.company.board.domain.user.dto.response.ResUserGetByIdDto;
import com.company.board.domain.user.dto.response.ResUserGetDto;
import com.company.board.domain.user.dto.response.ResUserUpdateDto;
import com.company.board.global.exception.CommonErrorCode;
import com.company.board.global.exception.CustomException;
import com.company.board.domain.user.exception.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final AuthRepository authRepository;
    private final PasswordEncryptionService passwordEncryptionService;
    private final AuditorAware<Long> auditorAware;

    public ResUserGetDto getAll() {
        List<AuthEntity> users = authRepository.findAll();
        return ResUserGetDto.from(users);
    }

    public ResUserGetByIdDto getById(Long userId) {
        AuthEntity user = authRepository.findById(userId)
                .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));
        return ResUserGetByIdDto.from(user);
    }

    @Transactional
    public ResUserUpdateDto update(Long userId, ReqUserUpdateDto request) {
        AuthEntity user = authRepository.findById(userId)
                .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

        // 변경이 제대로 이루어져있는 확인
        boolean updated = false;

        // 본래의 닉네임은 제외하고 중복 체크
        if (request.getUser().getNickname() != null
                && !user.getNickname().equals(request.getUser().getNickname())) {
            if (authRepository.existsByNicknameAndUserIdNot(request.getUser().getNickname(), userId)) {
                throw new CustomException(UserErrorCode.USER_NICKNAME_ALREADY_EXISTS);
            }
            user.updateNickname(request.getUser().getNickname());
            updated = true;
        }

        if (request.getUser().getPassword() != null &&
                !user.matchPassword(request.getUser().getPassword(), passwordEncryptionService)) {
            user.updatePassword(passwordEncryptionService.encode(request.getUser().getPassword()));
            updated = true;
        }

        if (request.getUser().getRole() != null && !user.getRole().equals(request.getUser().getRole())) {
            user.updateRole(request.getUser().getRole());
            updated = true;
        }

        // 변경된 값이 없을 때 Error
        if (!updated) {
            throw new CustomException(CommonErrorCode.NO_CHANGES_DETECTED);
        }

        return ResUserUpdateDto.from(user);
    }

    // 삭제
    @Transactional
    public void delete(Long userId) {
        AuthEntity user = authRepository.findById(userId)
                .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

        user.delete(auditorAware.getCurrentAuditor().get());
    }
}