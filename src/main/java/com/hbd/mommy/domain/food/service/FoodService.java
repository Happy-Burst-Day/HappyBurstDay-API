package com.hbd.mommy.domain.food.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbd.mommy.domain.food.mapper.FoodMapper;
import com.hbd.mommy.domain.food.model.dto.FoodDto;
import com.hbd.mommy.domain.food.repository.FoodRepository;
import com.hbd.mommy.domain.user.model.entity.User;
import com.hbd.mommy.domain.user.repository.UserRepository;
import com.hbd.mommy.global.error.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FoodService {
	private final UserRepository userRepository;
	private final FoodRepository foodRepository;

	public List<FoodDto> findFoods(String keyword, Long userId) {
		User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
		return foodRepository.findAllByNameKeyword(keyword).stream()
			.map(food -> FoodMapper.toFoodResponse(food, user.getBirthDate()))
			.toList();
	}

}
