package com.anys34.homework.infra.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "KaKaoClient", url = "https://kapi.kakao.com")
public interface KaKaoClient {
    @GetMapping(value = "/v2/user/me")
    String getInfo(@RequestHeader("Authorization") String accessToken);
}