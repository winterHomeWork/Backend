package com.anys34.homework.domain.food.domain.repository;

import com.anys34.homework.domain.food.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, String> {
}
