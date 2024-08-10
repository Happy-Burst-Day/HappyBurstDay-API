package com.hbd.mommy.domain.user.exception;

import org.springframework.http.HttpStatus;

import com.hbd.mommy.global.error.exception.LocalizedMessageException;

public class WrongPasswordException extends LocalizedMessageException {
    public WrongPasswordException() {
        super(HttpStatus.BAD_REQUEST, "invalid.password");
    }
}
