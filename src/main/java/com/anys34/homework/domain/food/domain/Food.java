package com.anys34.homework.domain.food.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Food {

    @Id
    @Column(name = "food_name")
    private String prdlstNm;

    private String nutrient;

    private String image;

    private String capacity;
}
