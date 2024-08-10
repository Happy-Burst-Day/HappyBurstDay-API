package com.hbd.mommy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbd.mommy.global.auth.jwt.AppAuthentication;
import com.hbd.mommy.global.auth.role.UserAuth;

@Controller
public class TestController {

	/**
	 * 테스트 API
	 *
	 * @return Hello
	 */
	@ResponseBody
	@GetMapping
	public String hello() {
		return "Hello";
	}

	/**
	 * 테스트 API (인증 필요)
	 *
	 * @return Hello
	 */
	@ResponseBody
	@GetMapping
	@UserAuth
	public String helloAuth(AppAuthentication auth) {
		return "Hello, %s".formatted(auth.getName());
	}
}
