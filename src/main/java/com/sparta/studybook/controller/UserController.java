package com.sparta.studybook.controller;

import com.sparta.studybook.dto.request.LoginRequestDto;
import com.sparta.studybook.dto.request.SignupRequestDto;
import com.sparta.studybook.dto.response.ResponseDto;
import com.sparta.studybook.security.UserDetailsImpl;
import com.sparta.studybook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST.value(), null));
        }

        userService.signup(signupRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto<>("회원가입 완료", HttpStatus.CREATED.value(), null));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>("로그인 cicd완료", HttpStatus.OK.value(), null));
    }

    // 회원 탈퇴
    @DeleteMapping("/deleteid")
    public ResponseEntity<ResponseDto> deleteId(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse response) {
        userService.deleteId(userDetails.getUser(), response);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>("회원탈퇴 완료", HttpStatus.OK.value(), null));
    }

}
