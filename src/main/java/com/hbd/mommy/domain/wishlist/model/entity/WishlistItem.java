package com.hbd.mommy.domain.wishlist.model.entity;

import static jakarta.persistence.FetchType.*;

import com.hbd.mommy.domain.food.model.entity.Food;
import com.hbd.mommy.domain.user.model.entity.User;
import com.hbd.mommy.global.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class WishlistItem extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "wishlist_item_id")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "food_id")
	private Food food;

	private int likes;
}
