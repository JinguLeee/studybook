package com.sparta.studybook.dto.response;

import com.sparta.studybook.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class PostDetailResponseDto {
    // 클라에서 게시글 어떤 내용을 보여줄지
    private Long id;
    private String title;
    private String content;
    private String loginid;
    private boolean islike;
    private boolean ismine;
    private Long totalCount;
    private String createdAt;

    // 게시글 response
    public PostDetailResponseDto(Post post, boolean islike, boolean ismine, Long totalCount) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.loginid = post.getUser().getLoginid();
        this.islike = islike;
        this.ismine = ismine;
        this.totalCount = totalCount;
        this.createdAt = post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd a h:mm"));
    }

}
