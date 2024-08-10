package com.hbd.mommy.global.error.exception;

import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;

import com.hbd.mommy.global.error.FieldErrorResult;

public class MissingRequiredParamterException extends LocalizedMessageException {
	public MissingRequiredParamterException(MissingServletRequestParameterException e) {
		super(e, HttpStatus.BAD_REQUEST, "");
	}

	@Override
	public List<Object> getMessages(MessageSource messageSource, Locale locale) {
		MissingServletRequestParameterException cause = (MissingServletRequestParameterException)getCause();
		FieldErrorResult fieldErrorResult = new FieldErrorResult(cause.getParameterName(),
			messageSource.getMessage("required.parameter", null, locale));
		return List.of(fieldErrorResult);
	}
}
