package com.yellowstone.boardback.domain.auth.service;

import com.yellowstone.boardback.domain.auth.dto.request.SignInRequestDto;
import com.yellowstone.boardback.domain.auth.dto.request.SignUpRequestDto;
import com.yellowstone.boardback.domain.auth.dto.response.SignInResponseDto;
import com.yellowstone.boardback.domain.auth.dto.response.SignUpResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
}
