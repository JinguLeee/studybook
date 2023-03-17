package com.sparta.studybook.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
public class SignupRequestDto { // 회원가입 정보 가져오기 Dto

    @NotBlank(message = "아이디는 필수입니다.")
    @Size(min = 4, max = 15)
    @Pattern(regexp = "^[a-z0-9]*$", message = "아이디는 4~10자 영문 소문자(a~z), 숫자(0~9)를 사용하세요.")
    private String userid;

    @NotBlank(message = "사용자이름은 필수입니다.")
    @Size(min = 2, max = 10)
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]*$", message = "사용자의 이름은 2~10자 영문, 숫자, 한글만 사용할 수 있습니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 4, max = 15)
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$!%*?&])[A-Za-z\\d@#$!%*?&]{8,15}$", message = "8~15글자, 영문자 1개, 숫자 1개, 특수문자 1개 꼭 입력해야합니다.")
    private String password;

    @NotBlank(message = "이메일은 필수입니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$!%*?&])[A-Za-z\\d@]{8,20}$", message = "8~15글자, 영문자 1개, 숫자 1개, 특수문자 @를 입력해야 합니다.")
    private String email;

}
