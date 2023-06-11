package com.example.sns.domain.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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

    @CreationTimestamp  // INSERT 시 자동으로 값을 채워줌
    private LocalDateTime createdAt;

    @UpdateTimestamp  // UPDATE 시 자동으로 값을 채워줌
    private LocalDateTime updatedAt;

//    @PrePersist // 디비에 INSERT 되기 직전에 실행
//    public void createDate() {
//        this.createdAt = LocalDateTime.now();
//    }
}
