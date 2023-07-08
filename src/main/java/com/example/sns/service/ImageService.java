package com.example.sns.service;

import com.example.sns.config.auth.PrincipalDetails;
import com.example.sns.domain.image.Image;
import com.example.sns.domain.image.ImageRepository;
import com.example.sns.handler.ex.CustomFileUploadException;
import com.example.sns.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {
    private final ImageRepository imageRepository;

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional
    public void imageUpload(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
        UUID uuid = UUID.randomUUID(); // uuid
        String imageFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename(); // 실제 파일명 (xxx.jpg)
        log.info("이미지 파일이름: {}", imageFileName);

        Path imageFilePath = Paths.get(uploadFolder + imageFileName);

        // 통신 IO -> 예외가 발생할 수 있다.
        try {
            Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
        } catch (Exception e) {
            throw new CustomFileUploadException("파일 업로드에 실패하였습니다");
        }

        Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
        imageRepository.save(image);
    }
}
