package com.sparta.studybook.entity;

import com.sparta.studybook.dto.request.SignupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userid;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    public User(SignupRequestDto signupRequestDto, String password) {
        this.userid = signupRequestDto.getUserid();
        this.password = password;
        this.username = signupRequestDto.getUsername();
        this.email = signupRequestDto.getEmail();
    }
}
