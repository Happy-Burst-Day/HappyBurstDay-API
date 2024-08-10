package com.hbd.mommy.domain.user.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RequestDkuStudentDto {

    @NotBlank
    private final String dkuStudentId;

    @NotBlank
    private final String dkuPassword;
}
