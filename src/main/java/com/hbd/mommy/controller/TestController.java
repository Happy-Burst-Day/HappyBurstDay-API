package com.hbd.mommy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
