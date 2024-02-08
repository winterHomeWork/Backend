package com.anys34.homework.domain.user.presentation.dto;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KakaoInfo {
    private String email;
    private String name;
}
