package com.company.board.global.security.details;

import com.company.board.domain.auth.exception.AuthErrorCode;
import com.company.board.domain.auth.repository.AuthRepository;
import com.company.board.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String nickname) {
        return authRepository.findByNickname(nickname)
                .map(CustomUserDetails::of)
                .orElseThrow(() -> new CustomException(AuthErrorCode.AUTH_USER_NOT_FOUND));
    }
}