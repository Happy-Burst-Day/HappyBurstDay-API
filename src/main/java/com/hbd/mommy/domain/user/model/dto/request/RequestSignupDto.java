package com.hbd.mommy.domain.user.model.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RequestSignupDto {

	@NotBlank
	@Email
	private final String email;

	@NotBlank
	@Size(min = 3, max = 200)
	private final String password;

	@NotNull
	private final LocalDate birthDate;
}
