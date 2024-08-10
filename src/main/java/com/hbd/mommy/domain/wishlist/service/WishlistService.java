package com.hbd.mommy.domain.wishlist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hbd.mommy.domain.food.model.entity.Food;
import com.hbd.mommy.domain.food.repository.FoodRepository;
import com.hbd.mommy.domain.user.model.entity.User;
import com.hbd.mommy.domain.user.repository.UserRepository;
import com.hbd.mommy.domain.wishlist.exception.AlreadyWishlistException;
import com.hbd.mommy.domain.wishlist.exception.NotFoundWishlistException;
import com.hbd.mommy.domain.wishlist.model.WishlistItemDto;
import com.hbd.mommy.domain.wishlist.model.dto.request.RequestAddWishlist;
import com.hbd.mommy.domain.wishlist.model.dto.response.ResponseWishlist;
import com.hbd.mommy.domain.wishlist.model.dto.response.ResponseWishlistRanking;
import com.hbd.mommy.domain.wishlist.model.entity.WishlistItem;
import com.hbd.mommy.domain.wishlist.repository.WishlistRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class WishlistService {

	private final UserRepository userRepository;
	private final FoodRepository foodRepository;
	private final WishlistRepository wishlistRepository;

	public ResponseWishlist getWishlist(Long userId) {
		List<WishlistItemDto> items = wishlistRepository.findAllByUserId(userId).stream()
			.map(WishlistItemDto::from)
			.toList();
		return new ResponseWishlist(items);
	}

	public ResponseWishlistRanking getWishlistRanking() {
		return new ResponseWishlistRanking(wishlistRepository.findTop3Foods().stream()
			.map(WishlistItemDto::from)
			.toList());
	}

	public WishlistItemDto addWishlist(Long userId, RequestAddWishlist request) {
		if (wishlistRepository.countByFoodId(request.getFoodId()) > 0) {
			throw new AlreadyWishlistException();
		}

		User user = userRepository.getReferenceById(userId);
		Food food = foodRepository.getReferenceById(request.getFoodId());
		WishlistItem item = WishlistItem.builder()
			.user(user)
			.food(food)
			.likes(0)
			.build();
		item = wishlistRepository.save(item);
		return WishlistItemDto.from(item);
	}

	public void deleteWishlist(Long userId, Long foodId) {
		wishlistRepository.deleteByFoodId(foodId);
	}

	public WishlistItemDto addLike(Long userId, Long foodId) {
		WishlistItem item = wishlistRepository.findByFoodId(foodId)
			.orElseThrow(NotFoundWishlistException::new);
		item.increaseLike();
		return WishlistItemDto.from(item);
	}
}
