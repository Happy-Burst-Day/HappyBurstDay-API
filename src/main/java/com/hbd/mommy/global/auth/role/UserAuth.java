package com.hbd.mommy.global.auth.role;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.annotation.Secured;

import com.hbd.mommy.global.auth.jwt.JwtProvider;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

/**
 * User 이상의 권한을 가진 사용자만 접근 가능한 API에 사용
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SecurityRequirement(name = JwtProvider.AUTHORIZATION)
@Secured(UserAuthNames.ROLE_USER)
public @interface UserAuth {
}
