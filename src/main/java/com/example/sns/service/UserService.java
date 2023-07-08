package com.example.sns.service;

import com.example.sns.domain.user.User;
import com.example.sns.domain.user.UserRepository;
import com.example.sns.handler.ex.CustomException;
import com.example.sns.handler.ex.CustomValidationApiException;
import com.example.sns.web.dto.user.UserProfileDto;
import com.example.sns.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)   // 영속성 컨텍스트의 더티 체킹을 하지 않음
    public UserProfileDto profile(Long userId, Long principalId) {
        UserProfileDto dto = new UserProfileDto();

        User userEntity = userRepository.findById(userId).orElseThrow(() -> {
            throw new CustomException("해당 프로필 페이지는 없는 페이지입니다");
        });

        dto.setUser(userEntity);
        dto.setPageOwner(userId == principalId);  // 1은 페이지 주인, -1은 주인이 아님
        return dto;
    }

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
