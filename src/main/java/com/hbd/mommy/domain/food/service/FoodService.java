package com.hbd.mommy.domain.food.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hbd.mommy.domain.food.model.FoodTag;
import com.hbd.mommy.domain.food.model.response.ResponseFood;
import com.hbd.mommy.domain.food.repository.FoodRepository;
import com.hbd.mommy.domain.user.model.entity.User;
import com.hbd.mommy.domain.user.repository.UserRepository;
import com.hbd.mommy.global.error.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodService {
	private final UserRepository userRepository;
	private final FoodRepository foodRepository;

	public List<ResponseFood> findFoods(Long userId, String keyword) {
		User user = userRepository.findById(userId)
			.orElseThrow(UserNotFoundException::new);
		return foodRepository.findAllByNameKeyword(keyword).stream()
			.map(food -> ResponseFood.builder()
				.name(food.getName())
				.imageUrl(food.getImageUrl())
				.tags(mapToFoodTags(food.getTags(), user))
				.build())
			.toList();
	}

	private List<FoodTag> mapToFoodTags(String tags, User user) {
		return List.of();
	}
}
