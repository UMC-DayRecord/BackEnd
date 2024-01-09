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
}
