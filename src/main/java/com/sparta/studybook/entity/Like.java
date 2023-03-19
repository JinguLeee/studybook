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

    @ManyToOne
    @JoinColumn(name = "postId", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    public Like(Post post, User user) {
        this.post = post;
        this.user = user;
    }

}
