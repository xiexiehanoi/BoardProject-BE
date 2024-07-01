package com.yellowstone.boardback.domain.user.controller;

import com.yellowstone.boardback.domain.user.dto.request.PatchNicknameRequestDto;
import com.yellowstone.boardback.domain.user.dto.request.PatchProfileImageRequestDto;
import com.yellowstone.boardback.domain.user.dto.response.GetSignInUserResponseDto;
import com.yellowstone.boardback.domain.user.dto.response.GetUserResponseDto;
import com.yellowstone.boardback.domain.user.dto.response.PatchNicknameResponseDto;
import com.yellowstone.boardback.domain.user.dto.response.PatchProfileImageResponseDto;
import com.yellowstone.boardback.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{email}")
    public ResponseEntity <? super GetUserResponseDto> getUser(
            @PathVariable("email") String email
    ){
        ResponseEntity <? super GetUserResponseDto> response = userService.getUser(email);
        return response;
    }

    @GetMapping("")
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(
            @AuthenticationPrincipal String email
    ){
        ResponseEntity<? super GetSignInUserResponseDto> response = userService.getSignInUser(email);
        return response;
    }

    @PatchMapping("/nickname")
    public ResponseEntity <? super PatchNicknameResponseDto> patchNickname(
            @RequestBody @Valid PatchNicknameRequestDto requestBody,
            @AuthenticationPrincipal String email
    ) {
        ResponseEntity<? super PatchNicknameResponseDto> response = userService.patchNickname(requestBody, email);
        return response;
    }

    @PatchMapping("/profile-image")
    public ResponseEntity <? super PatchProfileImageResponseDto> patchProfileImage(
            @RequestBody @Valid PatchProfileImageRequestDto requestBody,
            @AuthenticationPrincipal String email
    ){
        ResponseEntity<? super PatchProfileImageResponseDto> response = userService.patchProfileImage(requestBody, email);
        return response;
    }

}
