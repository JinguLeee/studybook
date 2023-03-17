package com.sparta.studybook.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "likelist")
@Getter
@NoArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int index;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    public Like(int index, Long postId, Long likeId, User user) {
        this.index = index;
        this.postId = postId;
        this.likeId = likeId;
        this.user = user;
    }

}
