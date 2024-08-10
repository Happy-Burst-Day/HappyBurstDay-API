package com.hbd.mommy.domain.wishlist.model.dto.response;

import java.util.List;

import com.hbd.mommy.domain.wishlist.model.WishlistItemDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResponseWishlist {
	private final List<WishlistItemDto> wishlist;
}
