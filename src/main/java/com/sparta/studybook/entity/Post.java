package com.sparta.studybook.entity;

import com.sparta.studybook.dto.request.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @OneToMany(orphanRemoval = true, mappedBy = "post")
    private List<Like> likes;

    public Post(PostRequestDto postRequestDto, User user) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.user = user;
    }

    public void updatePost(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
    }
}
