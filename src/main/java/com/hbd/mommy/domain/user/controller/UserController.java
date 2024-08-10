package com.hbd.mommy.domain.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hbd.mommy.domain.user.model.dto.request.RequestLoginDto;
import com.hbd.mommy.domain.user.model.dto.request.RequestRefreshTokenDto;
import com.hbd.mommy.domain.user.model.dto.request.RequestSignupDto;
import com.hbd.mommy.domain.user.model.dto.response.ResponseLoginDto;
import com.hbd.mommy.domain.user.model.dto.response.ResponseRefreshTokenDto;
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
	@PostMapping("/{signup-token}")
	public void signup(@Valid @RequestBody RequestSignupDto dto) {
		signupService.signup(dto);
	}

	/**
	 * 닉네임이 이미 존재하는지 검증.
	 * 닉네임이 이미 존재하는지 검증합니다. 만약 존재하지 않으면 OK를 반환하고,
	 * 이미 사용중인 경우에는 BAD_REQUEST 오류가 발생합니다.
	 *
	 * @param nickname 닉네임
	 */
	@GetMapping("/valid")
	public void validNickname(@RequestParam String nickname) {
		signupService.checkNickname(nickname);
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
	public ResponseRefreshTokenDto refreshToken(HttpServletRequest request,
		@Valid @RequestBody RequestRefreshTokenDto dto) {
		return userService.refreshToken(request, dto.getRefreshToken());
	}
}
