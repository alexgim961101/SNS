package com.example.sns.domain.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String website;
    private String bio;
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl;
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
