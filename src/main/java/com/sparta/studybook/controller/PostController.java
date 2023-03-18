package com.sparta.studybook.controller;

import com.sparta.studybook.dto.request.PostRequestDto;
import com.sparta.studybook.dto.response.LikeResponseDto;
import com.sparta.studybook.dto.response.PostResponseDto;
import com.sparta.studybook.dto.response.ResponseDto;
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
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.createPost(postRequestDto, userDetails.getUser());
    }

    // 전체 게시글 조회
    @GetMapping("/post")
    public List<PostResponseDto> getAllPost() {
        return postService.getAllPost();
    }

   // 게시글 상세 조회
   @GetMapping("/post/{postId}")
   public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
   }

    // 게시글 수정
    @PutMapping("/post/{postId}")
    public ResponseEntity<ResponseDto> updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.updatePost(id, postRequestDto, userDetails.getUser());
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK.value(), "수정 완료."));
    }

    // 게시글 삭제
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ResponseDto> deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(id, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.value(), "게시글 삭제"));
    }

    // 게시글 좋아요
    @PostMapping("/post/{postId}")
    public LikeResponseDto likePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.likepost(postId, userDetails.getUser());
    }

}
