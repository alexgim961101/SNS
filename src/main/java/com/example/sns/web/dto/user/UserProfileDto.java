package com.example.sns.web.dto.user;

import com.example.sns.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDto {
    private boolean pageOwner;
    private int imageCount;
    private User user;
}
