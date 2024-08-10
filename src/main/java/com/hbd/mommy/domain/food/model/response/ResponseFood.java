package com.hbd.mommy.domain.food.model.response;

import java.util.List;

import com.hbd.mommy.domain.food.model.FoodDiseaseWarning;
import com.hbd.mommy.domain.food.model.FoodSafeType;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ResponseFood {
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
