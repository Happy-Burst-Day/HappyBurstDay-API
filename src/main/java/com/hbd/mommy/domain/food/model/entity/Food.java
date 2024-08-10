package com.hbd.mommy.domain.food.model.entity;

import com.hbd.mommy.global.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

	@NotNull
	private String tags;

	@NotNull
	private String imageUrl;

	private int daysEatAfterBirth;
}
