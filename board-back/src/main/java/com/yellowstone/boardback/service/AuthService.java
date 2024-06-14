package com.yellowstone.boardback.service;

import com.yellowstone.boardback.dto.request.auth.SignInRequestDto;
import com.yellowstone.boardback.dto.request.auth.SignUpRequestDto;
import com.yellowstone.boardback.dto.response.auth.SignInResponseDto;
import com.yellowstone.boardback.dto.response.auth.SignUpResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
}
