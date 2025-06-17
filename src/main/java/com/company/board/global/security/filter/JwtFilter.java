package com.company.board.global.security.filter;

import com.company.board.domain.auth.exception.AuthErrorCode;
import com.company.board.global.exception.CustomException;
import com.company.board.global.model.Role;
import com.company.board.global.security.details.CustomUserDetails;
import com.company.board.domain.user.entity.UserEntity;
import com.company.board.global.security.jwt.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 로그인, 회원가입, 문서 관련 경로 제외
        String path = request.getRequestURI();
        if (path.equals("/v1/auth/signup") || path.equals("/v1/auth/signin")
                || path.contains("/swagger") || path.contains("/api-docs")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Authorization 헤더에서 JWT 추출
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = (authHeader != null && authHeader.startsWith("Bearer "))
                ? authHeader.substring(7) : null;

        if (token == null) {
            log.warn("Authorization 헤더 없음");
            throw new CustomException(AuthErrorCode.AUTH_TOKEN_MISSING);
        }

        // 만료된 토큰 체크
        if (jwtUtil.isExpired(token)) {
            log.warn("만료된 JWT: {}", token);
            throw new CustomException(AuthErrorCode.AUTH_TOKEN_EXPIRED);
        }

        try {
            // JWT에서 유저 정보 추출
            Long userId = jwtUtil.getUserId(token);
            String nickname = jwtUtil.getNickname(token);
            String role = jwtUtil.getRole(token);

            UserEntity user = UserEntity.of(userId, nickname, Role.valueOf(role));

            CustomUserDetails userDetails = CustomUserDetails.of(user);

            // SecurityContext에 인증 객체 설정
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            log.warn("JWT 인증 중 오류 발생: {}", e.getMessage());
            throw new CustomException(AuthErrorCode.AUTH_TOKEN_INVALID);
        }

        filterChain.doFilter(request, response);
    }
}