package com.sparta.studybook.dto.response;

import com.sparta.studybook.entity.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDto {
    // 클라에서 게시글 어떤 내용을 보여줄지
    private Long id;
    private String title;
    private String userid;
    private boolean islike;

    // 게시글 response
    public PostResponseDto(Post post, boolean islike) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.userid = post.getUser().getUserid();
        this.islike = islike;
    }

}
