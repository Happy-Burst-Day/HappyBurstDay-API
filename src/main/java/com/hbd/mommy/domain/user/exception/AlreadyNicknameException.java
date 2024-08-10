package com.hbd.mommy.domain.user.exception;

import org.springframework.http.HttpStatus;

import com.hbd.mommy.global.error.exception.LocalizedMessageException;

public class AlreadyNicknameException extends LocalizedMessageException {
	public AlreadyNicknameException() {
		super(HttpStatus.BAD_REQUEST, "already.nickname");
	}
}
