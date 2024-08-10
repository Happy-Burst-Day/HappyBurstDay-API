package com.hbd.mommy.domain.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbd.mommy.domain.user.exception.AlreadyNicknameException;
import com.hbd.mommy.domain.user.exception.WrongPasswordException;
import com.hbd.mommy.domain.user.model.dto.request.RequestLoginDto;
import com.hbd.mommy.domain.user.model.dto.response.ResponseLoginDto;
import com.hbd.mommy.domain.user.model.dto.response.ResponseRefreshTokenDto;
import com.hbd.mommy.domain.user.model.dto.response.ResponseUserInfoDto;
import com.hbd.mommy.domain.user.model.entity.User;
import com.hbd.mommy.domain.user.repository.UserRepository;
import com.hbd.mommy.global.auth.jwt.AuthenticationToken;
import com.hbd.mommy.global.auth.jwt.JwtProvider;
import com.hbd.mommy.global.error.exception.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;

	public ResponseLoginDto login(RequestLoginDto dto) {
		User user = userRepository.findByStudentId(dto.getStudentId())
			.orElseThrow(UserNotFoundException::new);

		if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
			AuthenticationToken token = jwtProvider.issue(user);
			return new ResponseLoginDto(token);
		} else {
			throw new WrongPasswordException();
		}
	}

	public ResponseUserInfoDto getUserInfo(Long userId) {
		User user = findUser(userId);
		return null;
	}

	public ResponseRefreshTokenDto refreshToken(HttpServletRequest request, String refreshToken) {
		String accessToken = jwtProvider.getAccessTokenFromHeader(request);
		AuthenticationToken token = jwtProvider.reissue(accessToken, refreshToken);
		return new ResponseRefreshTokenDto(token);
	}

	private User findUser(Long userId) {
		return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
	}

	public void checkAlreadyNickname(String nickname) {
		if (userRepository.findByNickname(nickname).isPresent()) {
			throw new AlreadyNicknameException();
		}
	}
}
