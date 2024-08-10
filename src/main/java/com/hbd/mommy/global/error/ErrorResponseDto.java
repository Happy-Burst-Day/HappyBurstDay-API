package com.hbd.mommy.global.error;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

import com.hbd.mommy.global.error.exception.LocalizedMessageException;

import lombok.Getter;

@Getter
public class ErrorResponseDto {
	private final String timestamp;
	private final String trackingId;
	private final HttpStatus status;
	private final String code;
	private final List<Object> message;

	public ErrorResponseDto(MessageSource messageSource, Locale locale, LocalizedMessageException e) {
		this.timestamp = LocalDateTime.now().toString();
		this.trackingId = UUID.randomUUID().toString();
		this.status = e.getStatus();
		this.code = e.getCode();
		this.message = e.getMessages(messageSource, locale);
	}
}
