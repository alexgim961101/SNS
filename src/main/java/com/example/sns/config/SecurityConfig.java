package com.example.sns.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration  // IoC에 등록
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // csrf : input form에 서버에서 보낸 form이라는 것을 확인하기 위한 토큰 -> 나중에 복잡해져서 일단 제거
        http.csrf().disable().cors().disable();

        return http.authorizeHttpRequests((request) -> {
            request.requestMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**").authenticated();

            request.anyRequest().permitAll();
        }).formLogin((formLogin) -> {
            formLogin.loginPage("/auth/signin");
            formLogin.defaultSuccessUrl("/");
        }).build();


    }
}
