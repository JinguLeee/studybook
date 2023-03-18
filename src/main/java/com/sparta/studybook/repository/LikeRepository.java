package com.sparta.studybook.repository;

import com.sparta.studybook.entity.Like;
import com.sparta.studybook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findBySeqAndLikeIdAndUser(int seq, Long likeId, User user);

    Long countBySeqAndLikeId(int seq, Long likeId);
}
