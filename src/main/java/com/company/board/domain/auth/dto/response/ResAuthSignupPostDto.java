package com.company.board.domain.auth.dto.response;

import com.company.board.domain.auth.entity.AuthEntity;
import com.company.board.global.model.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResAuthSignupPostDto {

    private User user;

    @Getter
    @Builder
    public static class User {
        private Long userId;
        private String loginId;
        private Role role;
    }

    public static ResAuthSignupPostDto from(AuthEntity authEntity) {
        return ResAuthSignupPostDto.builder()
                .user(User.builder()
                        .userId(authEntity.getUserId())
                        .loginId(authEntity.getLoginId())
                        .role(authEntity.getRole())
                        .build())
                .build();
    }
}