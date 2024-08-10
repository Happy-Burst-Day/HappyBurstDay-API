package com.hbd.mommy.domain.user.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestRefreshTokenDto {

    @NotBlank
    private final String refreshToken;
}
