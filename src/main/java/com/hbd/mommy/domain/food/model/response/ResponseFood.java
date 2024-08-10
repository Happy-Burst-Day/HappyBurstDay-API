package com.hbd.mommy.domain.food.model.response;

import java.util.List;

import com.hbd.mommy.domain.food.model.FoodTag;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ResponseFood {
	private final String name;
	private final String imageUrl;
	private final List<FoodTag> tags;
}
