package com.hbd.mommy.domain.user.model.dto.request;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RequestChangeBirthDateDto {

	@NotNull
	@Schema(description = "출산 예정일")
	private final LocalDate birthDate;
}
