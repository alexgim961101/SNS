package com.example.sns.service;

import com.example.sns.domain.subscribe.SubscribeRepository;
import com.example.sns.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SubscribeService {
    private final SubscribeRepository subscribeRepository;

    @Transactional
    public void subscribe(Long fromUserId, Long toUserId) {
        try {
            subscribeRepository.mSubscribe(fromUserId, toUserId);
        } catch (Exception e) {
            throw new CustomApiException("이미 구독을 하였습니다");
        }
    }
    @Transactional
    public void unSubscribe(Long fromUserId, Long toUserId) {
        subscribeRepository.mUnSubscribe(fromUserId, toUserId);
    }
}
