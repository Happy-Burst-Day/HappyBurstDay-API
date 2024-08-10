package com.hbd.mommy.global.auth.jwt;

public interface AuthenticationToken {
    String getAccessToken();

    String getRefreshToken();
}
