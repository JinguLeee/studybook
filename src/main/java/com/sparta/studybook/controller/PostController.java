package com.sparta.studybook.controller;

import com.sparta.studybook.dto.request.PostRequestDto;
import com.sparta.studybook.dto.response.*;
import com.sparta.studybook.entity.User;
import com.sparta.studybook.security.UserDetailsImpl;
import com.sparta.studybook.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 데이터 json 받기
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    // 게시글 작성
    @PostMapping("/post")
    public ResponseEntity<ResponseDto> createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.createPost(postRequestDto, userDetails.getUser());
        return ResponseEntity.ok().body(new ResponseDto<>("작성 완료", HttpStatus.CREATED.value(), null));
    }

    // 전체 게시글 조회
    @GetMapping("/post")
    public ResponseEntity<ResponseDto<List<PostResponseDto>>> getAllPost(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = null;
        if (userDetails != null) user = userDetails.getUser();
        return ResponseEntity.ok().body(new ResponseDto<>("조회 완료", HttpStatus.OK.value(), postService.getAllPost(user)));
    }

   // 게시글 상세 조회
   @GetMapping("/post/{postId}")
   public ResponseEntity<ResponseDto<PostDetailResponseDto>> getPost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
       User user = null;
       if (userDetails != null) user = userDetails.getUser();
       return ResponseEntity.ok().body(new ResponseDto<>("세부 조회 완료", HttpStatus.OK.value(), postService.getPost(postId, user)));
   }

    // 게시글 수정
    @PutMapping("/post/{postId}")
    public ResponseEntity<ResponseDto> updatePost(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.updatePost(postId, postRequestDto, userDetails.getUser());
        return ResponseEntity.ok().body(new ResponseDto<>("수정 완료", HttpStatus.OK.value(), null));
    }

    // 게시글 삭제
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ResponseDto> deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(postId, userDetails.getUser());
        return ResponseEntity.ok().body(new ResponseDto<>("삭제 완료", HttpStatus.OK.value(), null));
    }

    // 게시글 좋아요
    @PostMapping("/post/{postId}")
    public ResponseEntity<ResponseDto<LikeResponseDto>> likePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        LikeResponseDto likeResponseDto = postService.likepost(postId, userDetails.getUser());
        return ResponseEntity.ok().body(new ResponseDto<>("완료!", HttpStatus.OK.value(), likeResponseDto));
    }

}
