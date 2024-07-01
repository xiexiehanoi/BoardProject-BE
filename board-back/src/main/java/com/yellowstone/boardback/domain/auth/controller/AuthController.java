package com.yellowstone.boardback.domain.auth.controller;

import com.yellowstone.boardback.domain.auth.dto.request.SignInRequestDto;
import com.yellowstone.boardback.domain.auth.dto.request.SignUpRequestDto;
import com.yellowstone.boardback.domain.auth.dto.response.SignInResponseDto;
import com.yellowstone.boardback.domain.auth.dto.response.SignUpResponseDto;
import com.yellowstone.boardback.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(
            @RequestBody @Valid SignUpRequestDto requestBody
    ){
      ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
      return response;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(
            @RequestBody @Valid SignInRequestDto requestBody
    ) {
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }
}
