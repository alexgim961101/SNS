package com.example.sns.web.api;

import com.example.sns.config.auth.PrincipalDetails;
import com.example.sns.domain.user.User;
import com.example.sns.handler.ex.CustomValidationApiException;
import com.example.sns.service.UserService;
import com.example.sns.web.dto.CMRespDto;
import com.example.sns.web.dto.user.UserUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(@PathVariable Long id,
                               @Valid UserUpdateDto updateDto,
                               BindingResult bindingResult,
                               @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            throw new CustomValidationApiException("유효성 검사 실패", errorMap);
        } else {
            User userEntity = userService.userUpdate(id, updateDto.toEntity());
            principalDetails.setUser(userEntity);
            return new CMRespDto<>(1, "회원 수정 완료", userEntity);
        }
    }
}
