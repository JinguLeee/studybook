package com.sparta.studybook.service;

import com.sparta.studybook.dto.request.PostRequestDto;
import com.sparta.studybook.dto.response.LikeResponseDto;
import com.sparta.studybook.dto.response.PostResponseDto;
import com.sparta.studybook.entity.Like;
import com.sparta.studybook.entity.LikeEnum;
import com.sparta.studybook.entity.Post;
import com.sparta.studybook.entity.User;
import com.sparta.studybook.repository.LikeRepository;
import com.sparta.studybook.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    // 게시글 작성
    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto, User user) {
        // post객체 = 새로운 post객체에 postRequestDto을 담음
        Post post = postRepository.saveAndFlush(new Post(postRequestDto, user));
        return new PostResponseDto(post);
    }

    public LikeResponseDto likepost(Long postId, User user) {
        postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 없습니다.")
        );
        Optional<Like> like = likeRepository.findByIndexAndLikeIdAndUser(LikeEnum.POST.getIndex(), postId, user);
        boolean isLike = like.isEmpty();
        if (isLike){
            // 좋아요
            likeRepository.saveAndFlush(new Like(LikeEnum.POST.getIndex(), postId, postId, user));
        } else {
            // 좋아요 취소
            likeRepository.deleteById(like.get().getId());
        }
        Long likeCount = countLike(LikeEnum.POST.getIndex(), postId);
        return new LikeResponseDto(isLike,likeCount);
    }

    private Long countLike(int index, Long postId){
        return likeRepository.countByIndexAndLikeId(index, postId);
    }
}
