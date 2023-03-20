package com.sparta.studybook.repository;

import com.sparta.studybook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginid(String loginid);

    Optional<User> findByEmail(String email);
}
