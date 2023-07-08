package com.example.sns.web;

import com.example.sns.config.auth.PrincipalDetails;
import com.example.sns.handler.ex.CustomValidationException;
import com.example.sns.service.ImageService;
import com.example.sns.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ImageController {

    private final ImageService imageService;

    @GetMapping({"/", "/image/story"})
    public String story() {
        return "image/story";
    }

    @GetMapping("/image/popular")
    public String popular() {
        return "image/popular";
    }

    @GetMapping("/image/upload")
    public String upload() {
        return "image/upload";
    }

    @PostMapping("/image")
    public String imageUpload(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody ImageUploadDto imageUploadDto) {

        if(imageUploadDto.getFile().isEmpty()) {
            throw new CustomValidationException("이미지가 첨부되지 않았습니다. ", null);
        }

        imageService.imageUpload(imageUploadDto, principalDetails);
        return "redirect://user/" + principalDetails.getUser().getId();
    }
}
