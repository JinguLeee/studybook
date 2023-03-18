package com.sparta.studybook.controller;

import com.sparta.studybook.dto.request.LoginRequestDto;
import com.sparta.studybook.dto.request.SignupRequestDto;
import com.sparta.studybook.dto.response.ResponseDto;
import com.sparta.studybook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseDto> signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED.value(), "회원가입 완료."));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK.value(), "로그인 완료."));
    }

}
