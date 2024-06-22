package com.yellowstone.boardback.service.implement;

import com.yellowstone.boardback.dto.request.auth.SignInRequestDto;
import com.yellowstone.boardback.dto.request.auth.SignUpRequestDto;
import com.yellowstone.boardback.dto.response.ResponseDto;
import com.yellowstone.boardback.dto.response.auth.SignInResponseDto;
import com.yellowstone.boardback.dto.response.auth.SignUpResponseDto;
import com.yellowstone.boardback.entity.UserEntity;
import com.yellowstone.boardback.provider.JwtProvider;
import com.yellowstone.boardback.repository.UserRepository;
import com.yellowstone.boardback.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {


    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {

        try{

            // 중복 제약 조건 부분
            String email = dto.getEmail();
            boolean existedEmail = userRepository.existsByEmail(email);
            if(existedEmail) return SignUpResponseDto.duplicateEmail();

            String nickname = dto.getNickname();
            boolean existedNickname = userRepository.existsByNickname(nickname);
            if(existedNickname) return SignUpResponseDto.duplicateNickName();

            String telNumber = dto.getTelNumber();
            boolean existedTelNumber = userRepository.existsByTelNumber(telNumber);
            if(existedTelNumber) return SignUpResponseDto.duplicateTelNumber();

            String password = dto.getPassword();
            //패스워드 암호화
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignUpResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        String token = null;

        try {

            String email = dto.getEmail();

            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return SignInResponseDto.signInFailed();

            String password = dto.getPassword();
            String encodedPassword = userEntity.getPassword();

            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if (!isMatched) return SignInResponseDto.signInFailed();

            token = jwtProvider.create(email);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(token);
    }
}
