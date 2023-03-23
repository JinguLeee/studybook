package com.sparta.studybook.service;

import com.sparta.studybook.dto.request.PostRequestDto;
import com.sparta.studybook.dto.response.LikeResponseDto;
import com.sparta.studybook.dto.response.PostDetailResponseDto;
import com.sparta.studybook.dto.response.PostResponseDto;
import com.sparta.studybook.entity.Like;
import com.sparta.studybook.entity.Post;
import com.sparta.studybook.entity.User;
import com.sparta.studybook.repository.LikeRepository;
import com.sparta.studybook.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    // 게시글 작성
    @Transactional
    public void createPost(PostRequestDto postRequestDto, User user) {
        // post객체 = 새로운 post객체에 postRequestDto을 담음
        postRepository.saveAndFlush(new Post(postRequestDto, user));
    }

    // 전체 게시글 조회
    @Transactional
    public List<PostResponseDto> getAllPost(User user) {
        // 모든 게시글 작성일별 내림차순 리스트
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();

        List<PostResponseDto> postList = new ArrayList<>();
        for (Post post : posts) {
            boolean isLike = false;
            if (user != null) isLike = isLike(post, user);
            postList.add(new PostResponseDto(post, isLike, countLike(post)));
        }
        return postList;
    }

    // 게시글 상세 조회
    @Transactional
    public PostDetailResponseDto getPost(Long postId, User user) {
        // 게시글 유무
        Post post = checkPost(postId);
        boolean isLike = isLike(post, user);
        boolean isMine = isMine(post, user);
        Long countLike = countLike(post);
        return new PostDetailResponseDto(post, isLike, isMine, countLike);
    }

    // 내가 쓴 게시글인지 여부
    public boolean isMine(Post post, User user) {
        if (user == null) return false;
        return post.getUser().getId() == user.getId();
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
    @Transactional
    public void deletePost(Long postId, User user) {
        checkPost(postId);
        checkMyPost(postId, user);
        // orphanRemoval = true 조건으로 연관관계 모두 알아서 삭제)
        // likeRepository.deleteAllByPostId(postId);
        postRepository.deleteById(postId);
    }


    // 판별 함수
    public void checkMyPost(Long postId, User user) {
        postRepository.findByIdAndUser(postId, user)
                .orElseThrow(() -> new IllegalArgumentException("내가 쓴 게시글이 아닙니다."));
    }

    // 게시글 유무 판별
    public Post checkPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
    }

    // 좋아요, 좋아요 취소
    @Transactional
    public LikeResponseDto likepost(Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 없습니다.")
        );
        Optional<Like> like = likeRepository.findByPostAndUser(post, user);
        boolean isLike = like.isEmpty();
        if (isLike){
            // 좋아요
            likeRepository.saveAndFlush(new Like(post, user));
        } else {
            // 좋아요 취소
            likeRepository.deleteById(like.get().getId());
        }
        return new LikeResponseDto(isLike, countLike(post));
    }

    // 좋아요 개수 count
    private Long countLike(Post post){
        return likeRepository.countByPost(post);
    }

    // 좋아요 여부
    public boolean isLike(Post post, User user) {
        if (user == null) return false;
        Optional<Like> like = likeRepository.findByPostAndUser(post, user);
        return like.isPresent();
    }

}
