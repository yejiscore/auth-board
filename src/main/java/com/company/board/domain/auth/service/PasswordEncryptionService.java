package com.company.board.domain.auth.service;

public interface PasswordEncryptionService {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);
}