package com.anys34.homework.infra.feign.client;

import com.anys34.homework.domain.user.presentation.dto.KakaoInfo;
import com.anys34.homework.infra.feign.client.dto.KaKaoToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "KaKaoClient", url = "https://kauth.kakao.com")
public interface KaKaoClient {

    @GetMapping("/v2/user/me")
    KakaoInfo getInfo(@RequestHeader("Authorization") String accessToken);

    @GetMapping("/oauth/authorize")
    KaKaoToken getToken(@RequestParam("client_id") String restApiKey,
                        @RequestParam("redirect_uri") String redirectUrl,
                        @RequestParam("code") String code,
                        @RequestParam("grant_type") String grantType);
}