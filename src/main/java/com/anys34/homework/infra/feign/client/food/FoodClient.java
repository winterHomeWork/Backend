package com.anys34.homework.infra.feign.client.food;

import com.anys34.homework.infra.feign.client.food.res.HttpResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://apis.data.go.kr/B553748/CertImgListServiceV3")
public interface FoodClient {
     @GetMapping()
     HttpResponse foodClient(
             @RequestParam(name = "serviceKey") String key,
             @RequestParam(name = "returnType") String type
    );
}
