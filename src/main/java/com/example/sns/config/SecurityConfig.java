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
