package com.example.sns.web.dto.auth;


import lombok.Data;

@Data
public class SignupReqDto {
    private String username;
    private String password;
    private String email;
    private String name;
}
