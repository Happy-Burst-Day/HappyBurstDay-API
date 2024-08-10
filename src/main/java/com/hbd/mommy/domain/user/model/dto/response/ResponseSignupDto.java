package com.hbd.mommy.domain.user.model.dto.response;

import java.time.LocalDate;

import com.hbd.mommy.domain.user.model.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResponseSignupDto {
	private final String email;
	private final LocalDate birthDate;
}
