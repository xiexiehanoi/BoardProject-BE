package com.yellowstone.boardback.domain.user.service;

import com.yellowstone.boardback.domain.user.dto.request.PatchNicknameRequestDto;
import com.yellowstone.boardback.domain.user.dto.request.PatchProfileImageRequestDto;
import com.yellowstone.boardback.domain.user.dto.response.GetSignInUserResponseDto;
import com.yellowstone.boardback.domain.user.dto.response.GetUserResponseDto;
import com.yellowstone.boardback.domain.user.dto.response.PatchNicknameResponseDto;
import com.yellowstone.boardback.domain.user.dto.response.PatchProfileImageResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);
    ResponseEntity<? super GetUserResponseDto> getUser(String email);
    ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto,String email);
    ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto, String email);

}
