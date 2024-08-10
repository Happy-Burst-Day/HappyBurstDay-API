package com.hbd.mommy.domain.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbd.mommy.domain.user.exception.AlreadyEmailException;
import com.hbd.mommy.domain.user.model.dto.request.RequestSignupDto;
import com.hbd.mommy.domain.user.model.dto.response.ResponseSignupDto;
import com.hbd.mommy.domain.user.model.entity.User;
import com.hbd.mommy.domain.user.repository.UserRepository;
import com.hbd.mommy.global.auth.role.UserRole;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignupService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	@Transactional
	public ResponseSignupDto signup(RequestSignupDto dto) {
		if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
			throw new AlreadyEmailException();
		}

		String encryptedPassword = passwordEncoder.encode(dto.getPassword());
		User user = User.builder()
			.email(dto.getEmail())
			.birthDate(dto.getBirthDate())
			.password(encryptedPassword)
			.role(UserRole.USER)
			.build();
		userRepository.save(user);
		return new ResponseSignupDto(dto.getEmail(), dto.getBirthDate());
	}
}
