package com.hbd.mommy.domain.food.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbd.mommy.domain.food.mapper.FoodMapper;
import com.hbd.mommy.domain.food.model.dto.FoodDto;
import com.hbd.mommy.domain.food.repository.FoodRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FoodService {
	private final FoodRepository foodRepository;

	public List<FoodDto> findFoods(String keyword) {
		return foodRepository.findAllByNameKeyword(keyword).stream()
			.map(FoodMapper::toFoodResponse)
			.toList();
	}

}
