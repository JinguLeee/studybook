package com.sparta.studybook.service;

import com.sparta.studybook.dto.request.PostRequestDto;
import com.sparta.studybook.dto.response.PostResponseDto;
import com.sparta.studybook.entity.Post;
import com.sparta.studybook.entity.User;
import com.sparta.studybook.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 게시글 작성
    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto, User user) {
        // post객체 = 새로운 post객체에 postRequestDto을 담음
        Post post = postRepository.saveAndFlush(new Post(postRequestDto, user));
        return new PostResponseDto(post);
    }

    // 전체 게시글 조회
    @Transactional
    public List<PostResponseDto> getAllPost() {
        // 모든 게시글 작성일별 내림차순 리스트
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();

        List<PostResponseDto> postList = new ArrayList<>();
        for (Post post : posts) {
            postList.add(new PostResponseDto(post));
        }
        return postList;
    }

    // 게시글 상세 조회
    @Transactional
    public PostResponseDto getPost(Long postId) {
        // 게시글 유무
        Post post = checkPost(postId);
        return new PostResponseDto(post);
    }

    // 게시글 수정
    @Transactional
    public void updatePost(Long postId, PostRequestDto postRequestDto, User user) {
        Post post = checkPost(postId);
        checkMyPost(postId, user);

        // 게시글 있다면 post를 새로 갈아끼운다.
        post.updatePost(postRequestDto);
    }

    // 게시글 삭제
    public void deletePost(Long postId, User user) {
        checkPost(postId);
        checkMyPost(postId, user);
        postRepository.deleteById(postId);
    }


    // 판별 함수
    public void checkMyPost(Long postId, User user) {
        postRepository.findByIdAndUser(postId, user)
                .orElseThrow(() -> new IllegalArgumentException("내가쓴 게시글이 아닙니다."));
    }

    // 게시글 유무 판별
    public Post checkPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
    }


}
