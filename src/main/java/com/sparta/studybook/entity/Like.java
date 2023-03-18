package com.sparta.studybook.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "likes")
@Getter
@NoArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int seq;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    public Like(int seq, Long postId, Long likeId, User user) {
        this.seq = seq;
        this.postId = postId;
        this.likeId = likeId;
        this.user = user;
    }

}
