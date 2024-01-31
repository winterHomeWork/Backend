package com.anys34.homework.infra.feign.client;

import com.anys34.homework.infra.feign.client.dto.KaKaoToken;
import com.anys34.homework.infra.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

@FeignClient(name = "KaKaoClient", configuration = FeignConfig.class)
public interface KaKaoClient {

    @PostMapping
    KaKaoToken getToken(URI baseUrl, @RequestParam("client_id") String restApiKey,
                        @RequestParam("redirect_uri") String redirectUrl,
                        @RequestParam("code") String code,
                        @RequestParam("grant_type") String grantType);
}