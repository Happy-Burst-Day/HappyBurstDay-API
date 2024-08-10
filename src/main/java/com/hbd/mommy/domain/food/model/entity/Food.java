package com.hbd.mommy.domain.food.model.entity;

import static jakarta.persistence.EnumType.*;

import com.hbd.mommy.domain.food.model.FoodSafeType;
import com.hbd.mommy.global.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Food extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "food_id")
	private Long id;

	@NotNull
	private String name;

	private String tags;

	private String imageUrl;

	@Enumerated(STRING)
	private FoodSafeType safeType;

	@Column(columnDefinition = "LONGTEXT")
	private String diseaseWarning;

	private double calroie;

	private double vitaminC;

	private double iron;

	private double calcium;

	private double protein;

	private int daysEatAfterBirth;
}
