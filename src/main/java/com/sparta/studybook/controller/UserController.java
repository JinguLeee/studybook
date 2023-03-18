package com.sparta.studybook.controller;

import com.sparta.studybook.dto.request.LoginRequestDto;
import com.sparta.studybook.dto.request.SignupRequestDto;
import com.sparta.studybook.dto.response.ResponseDto;
import com.sparta.studybook.exception.RestApiException;
import com.sparta.studybook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@RequestBody @Valid SignupRequestDto signupRequestDto, BindingResult result) {
        if (result.hasErrors()) {
            RestApiException restApiException = new RestApiException();
            restApiException.setHttpStatus(HttpStatus.BAD_REQUEST);
            restApiException.setErrorMessage(result.getFieldError().getDefaultMessage());

            return new ResponseEntity(
                    restApiException,
                    HttpStatus.BAD_REQUEST
            );
        }
        userService.signup(signupRequestDto);
        return ResponseEntity.ok().body(new ResponseDto<>("회원가입 완료", HttpStatus.CREATED.value(), null));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return ResponseEntity.ok().body(new ResponseDto<>("로그인 완료", HttpStatus.OK.value(), null));
    }

}
