package com.sparta.studybook.service;

import com.sparta.studybook.dto.request.PostRequestDto;
import com.sparta.studybook.dto.response.PostResponseDto;
import com.sparta.studybook.entity.Post;
import com.sparta.studybook.entity.User;
import com.sparta.studybook.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
