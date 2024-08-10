package com.hbd.mommy.global.error.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends LocalizedMessageException {
    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "notfound.user");
    }
}
