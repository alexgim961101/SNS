package com.example.sns.web;

import com.example.sns.domain.user.User;
import com.example.sns.handler.ex.CustomValidationException;
import com.example.sns.service.AuthService;
import com.example.sns.web.dto.auth.SignupReqDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }

    // form으로 데이터가 날아오는 방식 : x-www-form-urlen
    @PostMapping("/auth/signup")
    public String signup(@Valid SignupReqDto signupReqDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            // <필드명, 메세지>
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
                log.info("field : {}, msg : {}", error.getField(), error.getDefaultMessage());
            }

            throw new CustomValidationException("유효성 검사 실패함", errorMap);
        } else {
            log.info(signupReqDto.toString());

            User user = signupReqDto.toEntity();
            log.info(user.toString());

            User userEntity = authService.join(user);
            log.info(userEntity.toString());

            return "auth/signin";
        }
    }
}
