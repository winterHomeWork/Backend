package com.anys34.homework.infra.feign.client.food;

import com.anys34.homework.infra.feign.client.food.res.FoodResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://apis.data.go.kr/1471000/FoodNtrIrdntInfoService1/getFoodNtrItdntList1")
public interface FoodClient {
     @GetMapping
     FoodResponse foodClient(
             @RequestParam(name = "ServiceKey") String key,
             @RequestParam(name = "type") String type
    );
}
