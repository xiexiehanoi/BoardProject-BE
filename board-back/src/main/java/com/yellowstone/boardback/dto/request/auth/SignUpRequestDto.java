package com.yellowstone.boardback.dto.request.auth;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {

    @NotBlank @Email //null, 빈문자열, 공백으로 이루어지지 않는
    private String email;

    @NotBlank @Size(min=8, max=20)
    private String password;

    @NotBlank
    private String nickname;

    @NotBlank @Pattern(regexp="^[0-9]{11,13}$")
    private String telNumber;

    @NotBlank
    private String address;
    private String addressDetail;

    @NotNull @AssertTrue
    private Boolean agreedPersonal;

}
