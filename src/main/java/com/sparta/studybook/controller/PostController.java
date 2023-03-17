package com.sparta.studybook.controller;

import com.sparta.studybook.dto.request.PostRequestDto;
import com.sparta.studybook.dto.response.LikeResponseDto;
import com.sparta.studybook.dto.response.PostResponseDto;
import com.sparta.studybook.security.UserDetailsImpl;
import com.sparta.studybook.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    // 게시글 작성
    @PostMapping("/post")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(postService.createPost(postRequestDto, userDetails.getUser()));
    }

    // 전체 게시글 조회
//    @GetMapping("/posts")
//    public List<PostResponseDto> getAllPost() {
//        return postService.getAllPost();
//    }


    // 게시글 좋아요
    @PostMapping("/post/{postId}")
    public LikeResponseDto likePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.likepost(postId, userDetails.getUser());
    }

}
