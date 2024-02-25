package com.anys34.homework.domain.food.presentation;

import com.anys34.homework.domain.food.service.SaveFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {
    private final SaveFoodService saveFoodService;

    @PostMapping
    public void foodSave(
            @RequestParam String key,
            @RequestParam String type,
            @RequestParam String pageNo,
            @RequestParam String numOfRows
    ) {
        saveFoodService.saveFood(key, type, pageNo, numOfRows);
    }
}
