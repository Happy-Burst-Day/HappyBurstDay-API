package com.hbd.mommy.global.error;

import java.io.IOException;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hbd.mommy.global.error.exception.LocalizedMessageException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExceptionHandlerFilter extends OncePerRequestFilter {

	private final ObjectMapper objectMapper;
	private final MessageSource messageSource;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (LocalizedMessageException ex) {
			writeErrorResponse(response, request.getLocale(), ex);
		} catch (Exception ex) {
			writeUnexpectedErrorResponse(response, request.getLocale(), ex);
		}
	}

	private void writeErrorResponse(HttpServletResponse response, Locale locale, LocalizedMessageException ex) throws
		IOException {
		ErrorResponseDto dto = new ErrorResponseDto(messageSource, locale, ex);
		log.error("A problem has occurred in filter: [id={}]", dto.getTrackingId(), ex);
		writeResponse(response, dto, ex.getStatus().value());
	}

	private void writeUnexpectedErrorResponse(HttpServletResponse response, Locale locale, Exception ex) throws
		IOException {
		ErrorResponseDto dto = new ErrorResponseDto(messageSource, locale, LocalizedMessageException.of(ex));
		log.error("Unexpected exception has occurred in filter: [id={}]", dto.getTrackingId(), ex);
		writeResponse(response, dto, 500);
	}

	private void writeResponse(HttpServletResponse response, Object dto, int statusCode) throws IOException {
		response.setStatus(statusCode);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");

		String exceptionMessage = objectMapper.writeValueAsString(dto);
		response.getWriter().write(exceptionMessage);
	}
}
