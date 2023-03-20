package com.sparta.studybook.repository;

import com.sparta.studybook.entity.Like;
import com.sparta.studybook.entity.Post;
import com.sparta.studybook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByPostAndUser(Post post, User user);

    Long countByPost(Post post);

    void deleteAllByUser(User user);

}
