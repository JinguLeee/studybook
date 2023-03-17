package com.sparta.studybook.service;

import com.sparta.studybook.dto.request.LoginRequestDto;
import com.sparta.studybook.dto.request.SignupRequestDto;
import com.sparta.studybook.dto.response.ResponseDto;
import com.sparta.studybook.entity.User;
import com.sparta.studybook.jwt.JwtUtil;
import com.sparta.studybook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {

        // 아이디
        String userid = signupRequestDto.getUserid();
        // 사용자 이름
        String username = signupRequestDto.getUsername();
        // 비밀번호
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        // 이메일
        String email = signupRequestDto.getEmail();

        Optional<User> found = userRepository.findByUserid(userid);
        if (found.isPresent()) { // isPresent : ture는 실행, false는 실행불가
            throw new IllegalArgumentException("중복된 아이디 입니다.");
        }

        User user = new User(userid, password, username, email);
        userRepository.save(user);

    }

    @Transactional
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String userid = loginRequestDto.getUserid();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByUserid(userid).orElseThrow(
                () -> new IllegalArgumentException("아이디가 없습니다.")
        );

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUserid()));


    }
}
