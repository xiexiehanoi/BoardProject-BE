package com.yellowstone.boardback.service.implement;

import com.yellowstone.boardback.dto.response.ResponseDto;
import com.yellowstone.boardback.dto.response.user.GetSignInUserResponseDto;
import com.yellowstone.boardback.entity.UserEntity;
import com.yellowstone.boardback.repository.UserRepository;
import com.yellowstone.boardback.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email) {

        UserEntity userEntity = null;

        try {

            userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return GetSignInUserResponseDto.notExistUser();

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSignInUserResponseDto.success(userEntity);


    }
}
