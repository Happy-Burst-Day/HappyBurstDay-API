package com.hbd.mommy.domain.user.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RequestLoginDto {

	@NotBlank
	@Email
	@Schema(description = "이메일", example = "aaa@naver.com")
	private final String email;

	@NotBlank
	@Schema(description = "비밀번호", example = "121212")
	private final String password;
}
