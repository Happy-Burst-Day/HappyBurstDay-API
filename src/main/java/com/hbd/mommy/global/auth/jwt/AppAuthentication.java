package com.hbd.mommy.global.auth.jwt;

import org.springframework.security.core.Authentication;

import com.hbd.mommy.global.auth.role.UserRole;

public interface AppAuthentication extends Authentication {

	Long getUserId();

	UserRole getUserRole();

	boolean isAdmin();
}
