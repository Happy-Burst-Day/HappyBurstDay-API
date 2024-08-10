package com.hbd.mommy.domain.wishlist.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class WishlistItemDto {
	private final Long id;
	private final String name;
}
