package com.hbd.mommy.domain.user.exception;

import org.springframework.http.HttpStatus;

import com.hbd.mommy.global.error.exception.LocalizedMessageException;

public class IllegalNicknameException extends LocalizedMessageException {
	public IllegalNicknameException() {
		super(HttpStatus.BAD_REQUEST, "invalid.nickname");
	}
}
