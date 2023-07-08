package com.example.sns.domain.subscribe;

import com.example.sns.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name="subscribe_uk",
                        columnNames = {"fromUserId", "toUserId"} // 실제 DB 컬럼명을 적어야 함
                )
        }
)
public class Subscribe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 나를 구독하는 사람
    @ManyToOne
    @JoinColumn(name = "fromUserId")
    private User fromUser;
    // 내가 구독하는 사람
    @ManyToOne
    @JoinColumn(name = "toUserId")
    private User toUser;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
