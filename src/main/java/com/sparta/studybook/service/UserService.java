package com.sparta.studybook.service;

import com.sparta.studybook.dto.request.LoginRequestDto;
import com.sparta.studybook.dto.request.SignupRequestDto;
import com.sparta.studybook.entity.User;
import com.sparta.studybook.jwt.JwtUtil;
import com.sparta.studybook.repository.LikeRepository;
import com.sparta.studybook.repository.PostRepository;
import com.sparta.studybook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {

        Optional<User> found = userRepository.findByLoginid(signupRequestDto.getLoginid());
        if (found.isPresent()) { // isPresent : ture는 실행, false는 실행불가
            throw new IllegalArgumentException("중복된 아이디 입니다.");
        }

        // 비밀번호
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        User user = new User(signupRequestDto, password);
        userRepository.save(user);

    }

    @Transactional
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String loginid = loginRequestDto.getLoginid();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByLoginid(loginid).orElseThrow(
                () -> new IllegalArgumentException("아이디가 없습니다.")
        );

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getLoginid()));


    }

    // 회원탈퇴
    @Transactional
    public void deleteId(User user, HttpServletResponse response) {
        // 좋아요 삭제 (orphanRemoval = true 조건으로 연관관계 모두 알아서 삭제)
        likeRepository.deleteAllByUser(user);
        // 게시글 삭제 (orphanRemoval = true 조건으로 연관관계 모두 알아서 삭제)
        postRepository.deleteAllByUser(user);
        // 아이디 삭제
        userRepository.deleteById(user.getId());

        // 로그아웃
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, null);
    }
}
