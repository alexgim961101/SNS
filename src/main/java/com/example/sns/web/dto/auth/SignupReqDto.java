package com.example.sns.web.dto.auth;


import com.example.sns.domain.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupReqDto {

    @Size(max = 20, message = "20자를 초과하였습니다.")
    private String username;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    // validation 여러 개를 써도 각각 상황에 맞춰서 작동하는듯?
    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .build();
    }
}
