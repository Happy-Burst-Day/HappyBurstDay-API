package com.hbd.mommy.domain.food.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hbd.mommy.domain.food.model.dto.FoodDto;
import com.hbd.mommy.domain.food.service.FoodService;
import com.hbd.mommy.global.auth.jwt.AppAuthentication;
import com.hbd.mommy.global.auth.role.UserAuth;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "음식", description = "음식 관련 api")
@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {

	private final FoodService foodService;

	/**
	 * 키워드로 음식 검색
	 *
	 * @param keyword 음식 키워드
	 * @return 음식 목록
	 */
	@GetMapping
	@UserAuth
	public List<FoodDto> findFood(AppAuthentication auth, @RequestParam("keyword") String keyword) {
		return foodService.findFoods(keyword, auth.getUserId());
	}
}
