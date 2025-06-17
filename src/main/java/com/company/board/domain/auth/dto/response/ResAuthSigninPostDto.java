package com.company.board.domain.auth.dto.response;

import com.company.board.domain.user.entity.UserEntity;
import com.company.board.global.model.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResAuthSigninPostDto {

    private String accessToken;
    private String refreshToken;
    private User user;

    @Getter
    @Builder
    public static class User {
        private Long userId;
        private String nickname;
        private Role role;
    }

    public static ResAuthSigninPostDto from(String accessToken, String refreshToken, UserEntity userEntity) {
        return ResAuthSigninPostDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .user(User.builder()
                        .userId(userEntity.getUserId())
                        .nickname(userEntity.getNickname())
                        .role(userEntity.getRole())
                        .build())
                .build();
    }
}