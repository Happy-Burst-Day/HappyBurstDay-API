package com.hbd.mommy.domain.food.mapper;

import java.util.Arrays;
import java.util.List;

import com.hbd.mommy.domain.food.model.FoodDiseaseWarning;
import com.hbd.mommy.domain.food.model.dto.FoodDto;
import com.hbd.mommy.domain.food.model.entity.Food;

public class FoodMapper {
	public static FoodDto toFoodResponse(Food food) {
		return FoodDto.builder()
			.id(food.getId())
			.name(food.getName())
			.imageUrl(food.getImageUrl())
			.tags(mapToFoodTags(food.getTags()))
			.calroie(food.getCalroie())
			.calcium(food.getCalcium())
			.protein(food.getProtein())
			.iron(food.getIron())
			.vitaminC(food.getVitaminC())
			.safeType(food.getSafeType())
			.diseaseWarnings(mapToDiseaseWarnings(food.getDiseaseWarning()))
			.build();
	}

	private static List<FoodDiseaseWarning> mapToDiseaseWarnings(String diseaseWarning) {
		return Arrays.stream(diseaseWarning.split("@"))
			.map(warning -> {
				String[] token = warning.split("#");
				return new FoodDiseaseWarning(token[0], token[1]);
			})
			.toList();
	}

	// TODO 구현 필요
	private static List<String> mapToFoodTags(String tags) {
		return List.of();
	}
}
