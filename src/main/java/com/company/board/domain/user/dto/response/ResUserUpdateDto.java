package com.company.board.domain.user.dto.response;

import com.company.board.domain.auth.entity.AuthEntity;
import com.company.board.global.model.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResUserUpdateDto {

    private User user;

    @Getter
    @Builder
    public static class User {
        private Long userId;
        private String nickname;
        private Role role;
    }

    public static ResUserUpdateDto from(AuthEntity user) {
        return ResUserUpdateDto.builder()
                .user(User.builder()
                        .userId(user.getUserId())
                        .nickname(user.getNickname())
                        .role(user.getRole())
                        .build())
                .build();
    }
}