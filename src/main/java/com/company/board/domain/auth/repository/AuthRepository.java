package com.company.board.domain.auth.repository;

import com.company.board.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByNickname(String nickname);
    UserEntity findByNickname(String nickname);
    boolean existsByNicknameAndUserIdNot(String nickname, Long userId);
}