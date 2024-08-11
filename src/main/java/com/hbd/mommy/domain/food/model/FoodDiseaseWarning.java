package com.hbd.mommy.domain.food.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FoodDiseaseWarning {
	private final FoodDiseaseType type;
	private final String title;
	private final String description;
}
