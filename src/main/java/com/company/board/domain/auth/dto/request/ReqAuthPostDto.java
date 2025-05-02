package com.company.board.domain.auth.dto.request;

import com.company.board.global.model.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReqAuthPostDto {

    @Valid
    @NotBlank(message = "아이디를 입력해주세요.")
    private String loginId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "권한을 입력해주세요.")
    private Role role;
}