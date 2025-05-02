package com.company.board.domain.auth.repository;

import com.company.board.domain.auth.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<AuthEntity, Long> {
    boolean existsByLoginId(String loginId);
    AuthEntity findByLoginId(String loginId);
}