package com.company.board.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // 개발 단계에서는 모든 요청을 허용
                        .anyRequest().permitAll()
                        // TODO: Security 작업 후 아래 설정으로 전환 필요 (회원가입/로그인만 허용)
//                        .requestMatchers("/v1/auth/signup", "/v1/auth/signin").permitAll()
//                        .anyRequest().authenticated()

                );
        return http.build();
    }
}