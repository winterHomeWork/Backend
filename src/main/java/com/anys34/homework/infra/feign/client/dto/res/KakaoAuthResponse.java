package com.anys34.homework.infra.feign.client.dto.res;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoAuthResponse {
    private String access_token;
}
