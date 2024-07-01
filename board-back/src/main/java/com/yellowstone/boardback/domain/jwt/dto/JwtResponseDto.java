package com.yellowstone.boardback.domain.jwt.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class JwtResponseDto {

    String grantType;
    String accessToken;
    String refreshToken;
    Long accessTokenExpiresIn;

    @Builder
    public JwtResponseDto(String grantType, String accessToken, String refreshToken, Long accessTokenExpiresIn) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
    }
}
