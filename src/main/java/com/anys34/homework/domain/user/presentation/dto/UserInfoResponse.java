package com.anys34.homework.domain.user.presentation.dto;

import com.anys34.homework.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoResponse {
    private String nickname;
    private String email;

    public UserInfoResponse(User user) {
        nickname = user.getName();
        email = user.getEmail();
    }
}
