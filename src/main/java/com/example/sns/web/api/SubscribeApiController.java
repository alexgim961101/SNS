package com.example.sns.web.api;

import com.example.sns.config.auth.PrincipalDetails;
import com.example.sns.service.SubscribeService;
import com.example.sns.web.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SubscribeApiController {

    private final SubscribeService subscribeService;

    // toUserId를 구독하기
    @PostMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<?> subscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long toUserId) {
        subscribeService.subscribe(principalDetails.getUser().getId(), toUserId);
        return new ResponseEntity<>(new CMRespDto<>(1, "구독하기 성동", null), HttpStatus.OK);
    }

    // toUserId를 구독취소하기
    @DeleteMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<?> unSubscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long toUserId) {
        subscribeService.unSubscribe(principalDetails.getUser().getId(), toUserId);
        return new ResponseEntity<>(new CMRespDto<>(1, "구독취소하기 성공", null), HttpStatus.OK);
    }
}
