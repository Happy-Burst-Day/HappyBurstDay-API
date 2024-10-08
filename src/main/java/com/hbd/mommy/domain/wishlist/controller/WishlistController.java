package com.hbd.mommy.domain.wishlist.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbd.mommy.domain.wishlist.model.WishlistItemDto;
import com.hbd.mommy.domain.wishlist.model.dto.request.RequestAddWishlist;
import com.hbd.mommy.domain.wishlist.model.dto.response.ResponseWishlist;
import com.hbd.mommy.domain.wishlist.model.dto.response.ResponseWishlistRanking;
import com.hbd.mommy.domain.wishlist.service.WishlistService;
import com.hbd.mommy.global.auth.jwt.AppAuthentication;
import com.hbd.mommy.global.auth.role.UserAuth;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "위시리스트", description = "임산부 찜 목록 관련 api")
@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistController {

	private final WishlistService wishlistService;

	/**
	 * 내 위시리스트 목록 조회
	 *
	 * @return 내 위시리스트 반환
	 */
	@GetMapping
	@UserAuth
	public ResponseWishlist getWishlist(AppAuthentication auth) {
		return wishlistService.getWishlist(auth.getUserId());
	}

	/**
	 * 다른 임산부들이 위시리스트에 많이 추가한 항목 top3
	 *
	 * @return 위시리스트 top3
	 */
	@GetMapping("/top")
	@UserAuth
	public ResponseWishlistRanking getWishlistRanking(AppAuthentication auth) {
		return wishlistService.getWishlistRanking(auth.getUserId());
	}

	/**
	 * 내 위시리스트에 음식 추가
	 *
	 * @param request 요청
	 * @return 추가된 뒤의 위시리스트 반환
	 */
	@PostMapping
	@UserAuth
	public WishlistItemDto addWishlist(AppAuthentication auth, @RequestBody RequestAddWishlist request) {
		return wishlistService.addWishlist(auth.getUserId(), request);
	}

	/**
	 * 내 위시리스트에서 음식 제거
	 *
	 * @param id 음식 아이디
	 * @return 삭제된 뒤의 위시리스트 반환
	 */
	@DeleteMapping("/{id}")
	@UserAuth
	public void deleteWishlist(AppAuthentication auth, @PathVariable("id") Long id) {
		wishlistService.deleteWishlist(auth.getUserId(), id);
	}

	/**
	 * 음식에 좋아요 +1
	 *
	 * @param id 음식 아이디
	 * @return 좋아요 증가 뒤의 위시리스트 반환
	 */
	@PatchMapping("/like/{id}")
	@UserAuth
	public WishlistItemDto addLike(AppAuthentication auth, @PathVariable("id") Long id) {
		return wishlistService.addLike(auth.getUserId(), id);
	}
}
