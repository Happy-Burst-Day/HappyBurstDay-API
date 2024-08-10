package com.hbd.mommy.domain.wishlist.exception;

import org.springframework.http.HttpStatus;

import com.hbd.mommy.global.error.exception.LocalizedMessageException;

public class AlreadyWishlistException extends LocalizedMessageException {
    public AlreadyWishlistException() {
        super(HttpStatus.BAD_REQUEST, "already.wishlist");
    }
}
