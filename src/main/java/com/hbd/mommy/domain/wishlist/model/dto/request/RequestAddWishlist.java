package com.hbd.mommy.domain.wishlist.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RequestAddWishlist {
	@NotNull
	private int foodId;
}
