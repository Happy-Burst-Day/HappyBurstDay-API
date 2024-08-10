package com.hbd.mommy.domain.user.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbd.mommy.domain.user.exception.AlreadyNicknameException;
import com.hbd.mommy.domain.user.exception.IllegalNicknameException;
import com.hbd.mommy.domain.user.model.dto.request.RequestSignupDto;
import com.hbd.mommy.domain.user.model.entity.User;
import com.hbd.mommy.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignupService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	@Transactional
	public void signup(RequestSignupDto dto) {
		checkNickname(dto.getNickname());

		String encryptedPassword = passwordEncoder.encode(dto.getPassword());
		User user = User.builder()
			.studentId(studentInfo.getStudentId())
			.password(encryptedPassword)
			.name(studentInfo.getStudentName())
			.nickname(dto.getNickname())
			.phone(phone)
			.major(major)
			.yearOfAdmission(studentInfo.getYearOfAdmission())
			.academicStatus(studentInfo.getStudentState())
			.status(UserStatus.ACTIVE)
			.role(UserRole.USER)
			.build();
		userRepository.save(user);
	}

	public void checkNickname(String nickname) {
		if (userRepository.findByNickname(nickname).isPresent()) {
			throw new AlreadyNicknameException();
		}

		if (StringUtils.isBlank(nickname)) {
			throw new IllegalNicknameException();
		}
	}
}
