package com.example.sns.web.dto.image;

import com.example.sns.domain.image.Image;
import com.example.sns.domain.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadDto {
    // NotBlack는 multipartFile에는 지원 X
    private MultipartFile file;
    private String caption;

    public Image toEntity(String url, User user) {
        return Image.builder()
                .postImageUrl(url)
                .caption(caption)
                .user(user)
                .build();
    }
}
