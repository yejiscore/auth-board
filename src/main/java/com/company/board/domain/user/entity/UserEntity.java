package com.company.board.domain.user.entity;

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
@Table(name = "p_user")
@SQLRestriction("deleted_at IS NULL")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 30)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public UserEntity(String nickname, String password, Role role) {
        this.nickname = nickname;
        this.password = password;
        this.role = role;
    }

    public static UserEntity create(String nickname, String encodedPassword, Role role) {
        return UserEntity.builder()
                .nickname(nickname)
                .password(encodedPassword)
                .role(role)
                .build();
    }

    public boolean matchPassword(String rawPassword, PasswordEncryptionService encoder) {
        return encoder.matches(rawPassword, this.password);
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updatePassword(String encryptedPassword) {
        this.password = encryptedPassword;
    }

    public void updateRole(Role role) {
        this.role = role;
    }
}