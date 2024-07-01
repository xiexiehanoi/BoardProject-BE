package com.yellowstone.boardback.domain.user.service.implement;

import com.yellowstone.boardback.domain.jwt.JwtProvider;
import com.yellowstone.boardback.domain.user.repository.UserRepository;
import com.yellowstone.boardback.domain.user.dto.request.PatchNicknameRequestDto;
import com.yellowstone.boardback.domain.user.dto.request.PatchProfileImageRequestDto;
import com.yellowstone.boardback.common.ResponseDto;
import com.yellowstone.boardback.domain.user.dto.response.GetSignInUserResponseDto;
import com.yellowstone.boardback.domain.user.dto.response.GetUserResponseDto;
import com.yellowstone.boardback.domain.user.dto.response.PatchNicknameResponseDto;
import com.yellowstone.boardback.domain.user.dto.response.PatchProfileImageResponseDto;
import com.yellowstone.boardback.domain.user.entity.UserEntity;
import com.yellowstone.boardback.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

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

    @Override
    public ResponseEntity<? super GetUserResponseDto> getUser(String email) {

        UserEntity userEntity = null;

        try {

            userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return GetUserResponseDto.noExistUser();

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserResponseDto.success(userEntity);
    }

    @Override
    public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto, String email) {
        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return PatchNicknameResponseDto.noExistUser();

            String nickname = dto.getNickname();
            boolean existedNickname = userRepository.existsByNickname(nickname);
            if (existedNickname) return PatchNicknameResponseDto.duplicateNickname();

            userEntity.setNickname(nickname);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PatchNicknameResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto, String email) {
        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return PatchProfileImageResponseDto.noExistUser();

            String profileImage = dto.getProfileImage();
            userEntity.setProfileImage(profileImage); // 수정된 부분
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PatchProfileImageResponseDto.success();
    }

    @Override
    public void logout(String token) {
        String email = jwtProvider.getEmailFromToken(token);
        if (email != null) {
            jwtProvider.storeRefreshToken(email, null); // Invalidate the refresh token in Redis
        }
    }
}
