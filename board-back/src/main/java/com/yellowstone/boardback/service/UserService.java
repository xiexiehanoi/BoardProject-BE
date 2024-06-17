package com.yellowstone.boardback.service;

import com.yellowstone.boardback.dto.response.user.GetSignInUserResponseDto;
import com.yellowstone.boardback.dto.response.user.GetUserResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);
    ResponseEntity<? super GetUserResponseDto> getUser(String email);

}
