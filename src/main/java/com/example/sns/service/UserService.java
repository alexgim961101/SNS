package com.example.sns.service;

import com.example.sns.domain.user.User;
import com.example.sns.domain.user.UserRepository;
import com.example.sns.handler.ex.CustomValidationApiException;
import com.example.sns.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User userUpdate(Long id, User user) {
        // 1. 영속화
        User userEntity = userRepository.findById(id).orElseThrow(() -> new CustomValidationApiException("찾을 수 없는 Id입니다")
        );
        // 2. 영속화된 오브젝트 수정 -> 더티체킹
        userEntity.setName(user.getName());

        String rawPwd = user.getPassword();
        String encPwd = bCryptPasswordEncoder.encode(rawPwd);

        userEntity.setPassword(encPwd);
        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());
        return userEntity;
    }
}
