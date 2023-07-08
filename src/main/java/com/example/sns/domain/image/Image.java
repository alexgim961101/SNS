package com.example.sns.domain.image;

import com.example.sns.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "image")
public class Image {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String caption; // 사진설명
    private String postImageUrl;  // 사진을 전송받아서 그 사진을 서버에 특정 폴더에 저장 -> DB에는 저장된 경로를 insert
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    // TODO: 이미지 좋아요

    // TODO: 댓글

    @CreationTimestamp
    private LocalDateTime createdAt;
}
