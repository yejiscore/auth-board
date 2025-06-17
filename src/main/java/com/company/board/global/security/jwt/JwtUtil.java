package com.company.board.global.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    private static final String BEARER_PREFIX = "Bearer ";

    private final SecretKey secretKey;
    private final long accessTokenExpiration;
    private final long refreshTokenExpiration;

    public JwtUtil(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-token-expiration}") long accessTokenExpiration,
            @Value("${jwt.refresh-token-expiration}") long refreshTokenExpiration
    ) {
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        this.secretKey = Keys.hmacShaKeyFor(decodedKey);

        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshTokenExpiration = refreshTokenExpiration;
    }

    // Access Token 생성
    public String createAccessToken(Long userId, String nickname, String role) {
        return Jwts.builder()
                .claim("userId", userId)
                .claim("nickname", nickname)
                .claim("role", role)
                .claim("type", "ACCESS")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .signWith(secretKey)
                .compact();
    }

    // Refresh Token 생성
    public String createRefreshToken(Long userId, String nickname, String role) {
        return Jwts.builder()
                .claim("userId", userId)
                .claim("nickname", nickname)
                .claim("role", role)
                .claim("type", "REFRESH")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .signWith(secretKey)
                .compact();
    }

    // JWT 내부에 담긴 정보(Claims)를 꺼내는 공통 파싱 메서드
    private Claims parseClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(removeBearerPrefix(token))
                    .getPayload();
        }
        // 토큰 만료
        catch (ExpiredJwtException e) {
            return e.getClaims();
        }
        // 파싱 중 오류 발생
        catch (JwtException | IllegalArgumentException e) {
            log.warn("Failed to parse claims from token: {}", e.getMessage());
            throw new RuntimeException("Invalid JWT token");
        }
    }

    // 토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Bearer prefix 제거
    private String removeBearerPrefix(String token) {
        if (token != null && token.startsWith(BEARER_PREFIX)) {
            return token.substring(BEARER_PREFIX.length());
        }
        return token;
    }

    // Claims에서 정보 추출
    public Long getUserId(String token) {
        return parseClaims(token).get("userId", Long.class);
    }
    public String getNickname(String token) {
        return parseClaims(token).get("nickname", String.class);
    }
    public String getRole(String token) {
        return parseClaims(token).get("role", String.class);
    }

    // 토큰 만료 여부 확인
    public boolean isExpired(String token) {
        return parseClaims(token).getExpiration().before(new Date());
    }

    // 리프레시 토큰 만료 예정 시간 계산
    public LocalDateTime getRefreshTokenExpirationTime() {
        return LocalDateTime.now().plus(Duration.ofMillis(refreshTokenExpiration));
    }
}