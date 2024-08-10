package com.hbd.mommy.domain.user.exception;

import org.springframework.http.HttpStatus;

import com.hbd.mommy.global.error.exception.LocalizedMessageException;

public class AlreadyEmailException extends LocalizedMessageException {
    public AlreadyEmailException() {
        super(HttpStatus.BAD_REQUEST, "already.email");
    }
}
