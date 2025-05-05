package com.company.board.domain.user.dto.request;

import com.company.board.global.model.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReqUserUpdateDto {

    @Valid
    private User user;

    @Getter
    public static class User {
        @NotBlank(message = "닉네임을 입력해주세요.")
        private String nickname;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;

        @NotNull(message = "권한을 입력해주세요.")
        private Role role;
    }
}