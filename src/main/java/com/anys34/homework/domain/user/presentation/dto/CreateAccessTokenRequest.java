package com.anys34.homework.domain.user.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CreateAccessTokenRequest {
    @NotNull
    private String refreshToken;
}
