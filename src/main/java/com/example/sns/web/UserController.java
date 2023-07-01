package com.example.sns.web;

import com.example.sns.config.auth.PrincipalDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class UserController {

    @GetMapping("/user/{id}")
    public String profile(@PathVariable Long id) {
        return "user/profile";
    }

    //TODO: 세션 내에 유저 정보 확인하기 2가지
    @GetMapping("/user/{id}/update")
    public String update(@PathVariable Long id, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {

        log.info(principalDetails.getUser().toString());

        PrincipalDetails principal = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(principal.getUser().toString());

        model.addAttribute("principal", principalDetails.getUser());

        return "user/update";
    }
}
