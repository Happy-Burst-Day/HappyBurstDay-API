package com.hbd.mommy.domain.user.model.dto.response;

import java.time.LocalDate;

import com.hbd.mommy.domain.user.model.entity.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResponseUserInfoDto {
	private final String email;
	private final LocalDate birthDate;

	public static ResponseUserInfoDto from(User user) {
		return new ResponseUserInfoDto(user.getEmail(), user.getBirthDate());
	}
}
