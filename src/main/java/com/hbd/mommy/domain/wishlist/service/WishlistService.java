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
import com.hbd.mommy.global.error.exception.UserNotFoundException;

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
		User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
		List<WishlistItemDto> items = wishlistRepository.findAllByUserId(userId).stream()
			.map(item -> WishlistItemDto.from(item, user.getBirthDate()))
			.toList();
		return new ResponseWishlist(items);
	}

	public ResponseWishlistRanking getWishlistRanking(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
		return new ResponseWishlistRanking(wishlistRepository.findTop3Foods().stream()
			.map(item -> WishlistItemDto.from(item, user.getBirthDate()))
			.toList());
	}

	public WishlistItemDto addWishlist(Long userId, RequestAddWishlist request) {
		if (wishlistRepository.countByFoodId(request.getFoodId()) > 0) {
			throw new AlreadyWishlistException();
		}

		User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
		Food food = foodRepository.getReferenceById(request.getFoodId());
		WishlistItem item = WishlistItem.builder()
			.user(user)
			.food(food)
			.likes(0)
			.build();
		item = wishlistRepository.save(item);
		return WishlistItemDto.from(item, user.getBirthDate());
	}

	public void deleteWishlist(Long userId, Long foodId) {
		wishlistRepository.deleteByFoodId(foodId);
	}

	public WishlistItemDto addLike(Long userId, Long foodId) {
		User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
		WishlistItem item = wishlistRepository.findByFoodId(foodId)
			.orElseThrow(NotFoundWishlistException::new);
		item.increaseLike();
		return WishlistItemDto.from(item, user.getBirthDate());
	}
}
