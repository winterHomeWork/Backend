package com.anys34.homework.infra.feign.client;

import com.anys34.homework.domain.user.presentation.dto.KakaoInfo;
import com.anys34.homework.infra.feign.client.dto.KaKaoToken;
import com.anys34.homework.infra.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

@FeignClient(name = "KaKaoClient", url = "https://kauth.kakao.com/oauth")
public interface KaKaoClient {

    @PostMapping
    KakaoInfo getInfo(URI baseUrl, @RequestHeader("Authorization") String accessToken);

    @PostMapping
    KaKaoToken getToken(URI baseUrl, @RequestParam("client_id") String restApiKey,
                        @RequestParam("redirect_uri") String redirectUrl,
                        @RequestParam("code") String code,
                        @RequestParam("grant_type") String grantType);
}