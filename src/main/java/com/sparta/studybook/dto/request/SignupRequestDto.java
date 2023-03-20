package com.sparta.studybook.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
public class SignupRequestDto { // 회원가입 정보 가져오기 Dto

    @NotBlank(message = "아이디는 필수입니다.")
    @Pattern(regexp = "^*[a-z0-9]{8,16}$",
            message = "아이디는 소문자와 숫자 포함된 8자 ~ 16자의 아이디여야 합니다.")
    private String loginid;

    @NotBlank(message = "사용자이름은 필수입니다.")
    @Pattern(regexp = "^*[가-힣a-zA-Z]{2,10}$", message = "사용자의 이름은 2~10자 한글, 영어만 사용할 수 있습니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Pattern(regexp = "^*(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$!%*?&])[A-Za-z\\d@#$!%*?&]{8,15}$", message = "8~15글자, 영문자 1개, 숫자 1개, 특수문자 1개 꼭 입력해야합니다.")
    private String password;

    @NotBlank(message = "이메일은 필수입니다.")
    @Pattern(regexp = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$", message = "이메일 형식에 맞지 않습니다.")
    private String email;

}
