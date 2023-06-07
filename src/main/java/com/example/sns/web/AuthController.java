package com.example.sns.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {


    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }
}
