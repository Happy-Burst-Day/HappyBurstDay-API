package com.hbd.mommy.domain.wishlist.exception;

import org.springframework.http.HttpStatus;

import com.hbd.mommy.global.error.exception.LocalizedMessageException;

public class NotFoundWishlistException extends LocalizedMessageException {
    public NotFoundWishlistException() {
        super(HttpStatus.NOT_FOUND, "notfound.wishlist");
    }
}
