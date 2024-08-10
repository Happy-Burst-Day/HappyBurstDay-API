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
	private final Long id;
	private final String name;
	private final String imageUrl;
	private final FoodSafeType safeType;
	private final List<String> tags;
	private final List<FoodDiseaseWarning> diseaseWarnings;
	private final double calroie;
	private final double vitaminC;
	private final double iron;
	private final double calcium;
	private final double protein;
	private final long dday;
}
