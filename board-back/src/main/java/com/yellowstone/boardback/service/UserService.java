package com.yellowstone.boardback.service;

import com.yellowstone.boardback.dto.response.user.GetSignInUserResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);

}
