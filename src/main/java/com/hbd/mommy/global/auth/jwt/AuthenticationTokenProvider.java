package com.hbd.mommy.global.auth.jwt;

import org.springframework.security.core.Authentication;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationTokenProvider {
	String getAccessTokenFromHeader(HttpServletRequest request);

	Authentication getAuthentication(String accessToken);

}
