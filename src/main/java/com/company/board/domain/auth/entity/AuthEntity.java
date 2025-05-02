package com.company.board.domain.auth.entity;

import com.company.board.domain.auth.service.PasswordEncryptionService;
import com.company.board.global.entity.BaseEntity;
import com.company.board.global.model.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "p_auth")
@SQLRestriction("deleted_at IS NULL")
public class AuthEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public AuthEntity(String loginId, String password, Role role) {
        this.loginId = loginId;
        this.password = password;
        this.role = role;
    }

    public static AuthEntity create(String loginId, String encodedPassword, Role role) {
        return AuthEntity.builder()
                .loginId(loginId)
                .password(encodedPassword)
                .role(role)
                .build();
    }

    public boolean matchPassword(String rawPassword, PasswordEncryptionService encoder) {
        return encoder.matches(rawPassword, this.password);
    }
}