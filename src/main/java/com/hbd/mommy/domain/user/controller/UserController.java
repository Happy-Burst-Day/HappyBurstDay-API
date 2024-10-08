package com.hbd.mommy.domain.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbd.mommy.domain.user.model.dto.request.RequestChangeBirthDateDto;
import com.hbd.mommy.domain.user.model.dto.request.RequestLoginDto;
import com.hbd.mommy.domain.user.model.dto.request.RequestRefreshTokenDto;
import com.hbd.mommy.domain.user.model.dto.request.RequestSignupDto;
import com.hbd.mommy.domain.user.model.dto.response.ResponseLoginDto;
import com.hbd.mommy.domain.user.model.dto.response.ResponseSignupDto;
import com.hbd.mommy.domain.user.model.dto.response.ResponseUserInfoDto;
import com.hbd.mommy.domain.user.service.SignupService;
import com.hbd.mommy.domain.user.service.UserService;
import com.hbd.mommy.global.auth.jwt.AppAuthentication;
import com.hbd.mommy.global.auth.role.UserAuth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "사용자", description = "사용자 인증 및 정보 관련 api")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final SignupService signupService;

	/**
	 * 내 정보 조회
	 *
	 * @return 내 정보
	 */
	@GetMapping
	@UserAuth
	public ResponseUserInfoDto getMyInfo(AppAuthentication auth) {
		return userService.getUserInfo(auth.getUserId());
	}

	/**
	 * 회원가입
	 *
	 * @param dto 요청 Body
	 */
	@PostMapping
	public ResponseSignupDto signup(@Valid @RequestBody RequestSignupDto dto) {
		return signupService.signup(dto);
	}

	/**
	 * 로그인
	 *
	 * @param dto 요청 body
	 * @return 로그인 인증 정보
	 */
	@PostMapping("/login")
	public ResponseLoginDto login(@Valid @RequestBody RequestLoginDto dto) {
		return userService.login(dto);
	}

	/**
	 * 토큰 재발급
	 *
	 * @param dto 요청 body
	 * @return 재발급된 로그인 인증 정보
	 */
	@PostMapping("/reissue")
	@UserAuth
	public ResponseLoginDto refreshToken(HttpServletRequest request,
		@Valid @RequestBody RequestRefreshTokenDto dto) {
		return userService.refreshToken(request, dto.getRefreshToken());
	}

	/**
	 * 출산예정일 변경
	 *
	 * @param request 요청
	 * @return 변경 후의 사용자 정보
	 */
	@PatchMapping("/birthdate")
	@UserAuth
	public ResponseUserInfoDto changeBirthDate(AppAuthentication auth,
		@Valid @RequestBody RequestChangeBirthDateDto request){
		return userService.changeBirthDate(auth.getUserId(), request);
	}
}
