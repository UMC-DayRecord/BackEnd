package com.umc5th.dayrecord.service.PostService;

import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostQueryServiceImpl implements PostQueryService {

    private final PostRepository postRepository;

    @Override
    public Slice<Post> getPostList(Long userId, Integer page) {
        Slice<Post> postList = postRepository.findByPost(userId, PageRequest.of(page, 3));
        return postList;
    }

    @Override
    public Post getPost(Long postId) {
        return postRepository.findById(postId).get();
    }

    public Slice<Post> getSearchList(Long userId, String query, Integer page) {
        Slice<Post> postList = postRepository.findBySearchPost(userId, query, PageRequest.of(page, 3));
        return postList;
    }

    public Post getPostDetailInfo(Long postId) {
        Post post = postRepository.findById(postId).get();
        return post;
    }

    @Override
    public Boolean existById(Long postId) {
        return postRepository.existsById(postId);
    }
}
