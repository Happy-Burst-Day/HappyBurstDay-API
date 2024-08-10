package com.hbd.mommy.domain.wishlist.model;

import java.time.LocalDate;

import com.hbd.mommy.domain.food.mapper.FoodMapper;
import com.hbd.mommy.domain.food.model.dto.FoodDto;
import com.hbd.mommy.domain.wishlist.model.entity.WishlistItem;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class WishlistItemDto {
	private final FoodDto food;
	private final int likes;

	public static WishlistItemDto from(WishlistItem item, LocalDate birthDate) {
		return new WishlistItemDto(FoodMapper.toFoodResponse(item.getFood(), birthDate), item.getLikes());
	}
}
