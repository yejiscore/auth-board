package com.company.board.domain.user.dto.response;

import com.company.board.domain.user.entity.UserEntity;
import com.company.board.global.model.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class ResUserGetDto {

    private List<User> users;

    @Getter
    @Builder
    public static class User {
        private Long userId;
        private String nickname;
        private Role role;
    }

    public static ResUserGetDto from(List<UserEntity> authEntities) {
        List<User> userList = authEntities.stream()
                .map(authEntity -> User.builder()
                        .userId(authEntity.getUserId())
                        .nickname(authEntity.getNickname())
                        .role(authEntity.getRole())
                        .build())
                .collect(Collectors.toList());

        return ResUserGetDto.builder()
                .users(userList)
                .build();
    }
}