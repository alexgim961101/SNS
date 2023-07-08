package com.example.sns.domain.user;


import com.example.sns.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    private String website;
    private String bio;
    @Column(nullable = false)
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl;
    @Column(nullable = false)
    private String role;

    // Image 클래스의 필드명을 넣어줘야함 -> 나는 연관관계의 주인이 아니다 -> 테이블에 컬럼을 만들지 마
    // User를 select할 때, 등록되 이미지들을 다 가져워
    // Lazy일 때는 User를 select 할때, image들을 한번에 가져오지마 (필요할 때만 가져오는 것)
    // Eager : 반대
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"user"})
    private List<Image> images = new ArrayList<>();

    @CreationTimestamp  // INSERT 시 자동으로 값을 채워줌
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp  // UPDATE 시 자동으로 값을 채워줌
    @Column(updatable = true)
    private LocalDateTime updatedAt;

//    @PrePersist // 디비에 INSERT 되기 직전에 실행
//    public void createDate() {
//        this.createdAt = LocalDateTime.now();
//    }
}
