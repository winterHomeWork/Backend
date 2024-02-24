package com.anys34.homework.domain.food.service;

import com.anys34.homework.infra.feign.client.food.FoodClient;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@Server
@RequiredArgsConstructor
public class SaveFoodService {
    private final FoodClient foodClient;

    @Transactional
    public void saveFood() {

    }
}
