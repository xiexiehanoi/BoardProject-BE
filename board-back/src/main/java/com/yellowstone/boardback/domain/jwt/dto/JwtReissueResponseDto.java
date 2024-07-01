package com.yellowstone.boardback.domain.jwt.dto;

import lombok.Builder;
import org.springframework.http.ResponseEntity;

@Builder
public record JwtReissueResponseDto (
        String accessToken,
        String refreshToken,
        Long accessTokenExpiresIn
){
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    public static ResponseEntity<JwtReissueResponseDto> success(JwtRequestDto requestDto, JwtResponseDto responseDto) {
        return ResponseEntity
                .ok()
                .header(AUTHORIZATION_HEADER, BEARER_PREFIX + responseDto.getAccessToken())
                .body(
                        JwtReissueResponseDto.builder()
                                .accessToken(responseDto.getAccessToken())
                                .refreshToken(requestDto.getRefreshToken())
                                .accessTokenExpiresIn(responseDto.getAccessTokenExpiresIn())
                                .build()
                );
    }
}
