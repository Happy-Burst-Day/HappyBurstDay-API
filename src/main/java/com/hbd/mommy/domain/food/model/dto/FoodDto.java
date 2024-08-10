package com.hbd.mommy.domain.food.model.dto;

import java.util.List;

import com.hbd.mommy.domain.food.model.FoodDiseaseWarning;
import com.hbd.mommy.domain.food.model.FoodSafeType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@RequiredArgsConstructor
public class FoodDto {
	private final String name;
	private final String imageUrl;
	private final FoodSafeType safeType;
	private final List<String> tags;
	private final List<FoodDiseaseWarning> diseaseWarnings;
	private double vitaminC;
	private double iron;
	private double calcium;
	private double protein;
}
