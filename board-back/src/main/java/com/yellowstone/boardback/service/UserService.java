package com.yellowstone.boardback.service;

import com.yellowstone.boardback.dto.request.user.PatchNicknameRequestDto;
import com.yellowstone.boardback.dto.request.user.PatchProfileImageRequestDto;
import com.yellowstone.boardback.dto.response.user.GetSignInUserResponseDto;
import com.yellowstone.boardback.dto.response.user.GetUserResponseDto;
import com.yellowstone.boardback.dto.response.user.PatchNicknameResponseDto;
import com.yellowstone.boardback.dto.response.user.PatchProfileImageResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);
    ResponseEntity<? super GetUserResponseDto> getUser(String email);
    ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto,String email);
    ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto, String email);

}
