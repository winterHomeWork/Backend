package com.anys34.homework.domain.food.service;

import com.anys34.homework.domain.food.domain.Food;
import com.anys34.homework.domain.food.domain.repository.FoodRepository;
import com.anys34.homework.infra.feign.client.food.FoodClient;
import com.anys34.homework.infra.feign.client.food.res.HttpResponse;
import com.anys34.homework.infra.feign.client.food.res.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class SaveFoodService {
    private final FoodClient foodClient;
    private final FoodRepository foodRepository;

    @Transactional
    public void saveFood(String key, String type, String pageNo, String numOfRows) {
        HttpResponse response = foodClient.foodClient(key, type, pageNo, numOfRows);

        for (Item item : response.getBody().getItems()) {
            Food food = Food.builder()
                    .prdlstNm(item.getPrdlstNm())
                    .nutrient(item.getNutrient())
                    .image(item.getImgurl1())
                    .capacity(item.getCapacity())
                    .build();

            // 데이터베이스에 저장
            foodRepository.save(food);
        }
    }
}
