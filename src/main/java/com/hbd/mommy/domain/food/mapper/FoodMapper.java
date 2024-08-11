package com.hbd.mommy.domain.food.mapper;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import com.hbd.mommy.domain.food.model.FoodDiseaseType;
import com.hbd.mommy.domain.food.model.FoodDiseaseWarning;
import com.hbd.mommy.domain.food.model.dto.FoodDto;
import com.hbd.mommy.domain.food.model.entity.Food;

public class FoodMapper {
	public static FoodDto toFoodResponse(Food food, LocalDate birthDate) {
		long dday = ChronoUnit.DAYS.between(LocalDate.now(), birthDate.plusDays(food.getDaysEatAfterBirth()));
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
			.dday(dday)
			.build();
	}

	private static List<FoodDiseaseWarning> mapToDiseaseWarnings(String diseaseWarning) {
		return Arrays.stream(diseaseWarning.split("@"))
			.map(warning -> {
				FoodDiseaseType type = FoodDiseaseType.GOOD;
				String[] token = warning.split("#");

				if (token[0].startsWith("!")) {
					token[0] = token[0].substring(1);
					type = FoodDiseaseType.BAD;
				}

				return new FoodDiseaseWarning(type, token[0], token[1]);
			})
			.toList();
	}

	// TODO 구현 필요
	private static List<String> mapToFoodTags(String tags) {
		return List.of();
	}
}
