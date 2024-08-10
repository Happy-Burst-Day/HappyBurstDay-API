package com.hbd.mommy.global.error;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.hbd.mommy.global.error.exception.BadRequestException;
import com.hbd.mommy.global.error.exception.BindingFailedException;
import com.hbd.mommy.global.error.exception.LocalizedMessageException;
import com.hbd.mommy.global.error.exception.MissingRequiredParamterException;
import com.hbd.mommy.global.error.exception.NotSupportedMethodException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ControllerAdvisor {

	private final MessageSource messageSource;

	@ExceptionHandler
	protected ResponseEntity<ErrorResponseDto> localizedException(LocalizedMessageException e, Locale locale) {
		ErrorResponseDto dto = new ErrorResponseDto(messageSource, locale, e);
		log.error("A problem has occurred in controller advice: [id={}]", dto.getTrackingId(), e);
		return ResponseEntity.status(e.getStatus()).body(dto);
	}

	@ExceptionHandler
	protected ResponseEntity<ErrorResponseDto> badParameter(HttpMessageNotReadableException e, Locale locale) {
		return localizedException(new BadRequestException(e), locale);
	}

	@ExceptionHandler
	protected ResponseEntity<ErrorResponseDto> badParameter(BindException e, Locale locale) {
		return localizedException(new BindingFailedException(e), locale);
	}

	@ExceptionHandler
	protected ResponseEntity<ErrorResponseDto> badParameter(MethodArgumentTypeMismatchException e, Locale locale) {
		return localizedException(new BadRequestException(e), locale);
	}

	@ExceptionHandler
	protected ResponseEntity<ErrorResponseDto> badParameter(MissingServletRequestParameterException e, Locale locale) {
		return localizedException(new MissingRequiredParamterException(e), locale);
	}

	@ExceptionHandler
	protected ResponseEntity<ErrorResponseDto> badMethod(HttpRequestMethodNotSupportedException e, Locale locale) {
		return localizedException(new NotSupportedMethodException(e), locale);
	}

	@ExceptionHandler
	protected ResponseEntity<ErrorResponseDto> unexpectedException(Exception e, Locale locale) {
		ErrorResponseDto dto = new ErrorResponseDto(messageSource, locale, LocalizedMessageException.of(e));
		log.error("Unexpected exception has occurred in controller advice: [id={}]", dto.getTrackingId(), e);
		return ResponseEntity.internalServerError().body(dto);
	}
}
