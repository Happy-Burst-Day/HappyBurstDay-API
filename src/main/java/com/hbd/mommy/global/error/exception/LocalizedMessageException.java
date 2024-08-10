package com.hbd.mommy.global.error.exception;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class LocalizedMessageException extends RuntimeException {

	private final HttpStatus status;
	private final String messageId;
	private final Object[] arguments;
	private final String customMessage = null;

	public LocalizedMessageException(HttpStatus status, String messageId, Object... arguments) {
		super(formatMessage(messageId, arguments));
		this.status = status;
		this.messageId = messageId;
		this.arguments = arguments;
	}

	public LocalizedMessageException(Throwable cause, HttpStatus status, String messageId, Object... arguments) {
		super(formatMessage(messageId, arguments), cause);
		this.status = status;
		this.messageId = messageId;
		this.arguments = arguments;
	}

	public List<Object> getMessages(MessageSource messageSource, Locale locale) {
		return List.of(Objects.requireNonNullElseGet(customMessage, () -> getDefaultMessage(messageSource, locale)));
	}

	public String getDefaultMessage(MessageSource messageSource, Locale locale) {
		return messageId + Arrays.stream(arguments)
			.map(Object::toString)
			.collect(Collectors.joining(","));
	}

	public String getCode() {
		return getClass().getSimpleName();
	}

	public static LocalizedMessageException of(Exception e) {
		return new UnexpectedException(e);
	}

	private static String formatMessage(String messageId, Object... arguments) {
		if (arguments.length == 0) {
			return messageId;
		}

		StringBuilder sb = new StringBuilder(messageId);
		sb.append(": ");
		for (int i = 0; i < arguments.length; i++) {
			sb.append(arguments[i]);
			if (i != arguments.length - 1) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}
}
