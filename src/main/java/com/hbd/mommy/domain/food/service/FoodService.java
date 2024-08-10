package com.hbd.mommy.domain.food.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hbd.mommy.domain.food.model.FoodDiseaseWarning;
import com.hbd.mommy.domain.food.model.entity.Food;
import com.hbd.mommy.domain.food.model.response.ResponseFood;
import com.hbd.mommy.domain.food.repository.FoodRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodService {
	private final FoodRepository foodRepository;

	public List<ResponseFood> findFoods(String keyword) {
		return foodRepository.findAllByNameKeyword(keyword).stream()
			.map(this::mapToFoodResponse)
			.toList();
	}

	private ResponseFood mapToFoodResponse(Food food) {
		return ResponseFood.builder()
			.name(food.getName())
			.imageUrl(food.getImageUrl())
			.tags(mapToFoodTags(food.getTags()))
			.calcium(food.getCalcium())
			.protein(food.getProtein())
			.iron(food.getIron())
			.vitaminC(food.getVitaminC())
			.diseaseWarnings(mapToDiseaseWarnings(food.getDiseaseWarning()))
			.build();
	}

	private List<FoodDiseaseWarning> mapToDiseaseWarnings(String diseaseWarning) {
		return Arrays.stream(diseaseWarning.split(","))
			.map(warning -> {
				String[] token = warning.split("#");
				return new FoodDiseaseWarning(token[0], token[1]);
			})
			.toList();
	}

	// TODO 구현 필요
	private List<String> mapToFoodTags(String tags) {
		return List.of();
	}
}
