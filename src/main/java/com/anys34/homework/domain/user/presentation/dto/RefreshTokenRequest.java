package com.anys34.homework.domain.user.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RefreshTokenRequest {
    private String refreshToken;
}
