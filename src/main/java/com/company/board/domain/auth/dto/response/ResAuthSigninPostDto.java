package com.company.board.domain.auth.dto.response;

import com.company.board.global.model.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResAuthSigninPostDto {
    private Long userId;
    private String loginId;
    private Role role;

    public static ResAuthSigninPostDto from(Long userId, String loginId, Role role) {
        return ResAuthSigninPostDto.builder()
                .userId(userId)
                .loginId(loginId)
                .role(role)
                .build();
    }
}