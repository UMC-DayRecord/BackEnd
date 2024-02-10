package com.umc5th.dayrecord.service.PostService;

import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.domain.User;
import com.umc5th.dayrecord.web.dto.PostDTO;

import org.springframework.data.domain.Slice;

public interface PostQueryService {

    Slice<Post> getPostList(Long userId, Integer page);
    Slice<Post> getPostList(User user, Integer page);


    Post getPost(Long postId);
    Slice<Post> getSearchList(Long userId, String query, Integer page);
    Post getPostDetailInfo(Long postId);
    Boolean existById(Long postId);

    void deletePost(Long postId);

    Post updatePost(PostDTO.editPostRequestDTO request, Long postId);

    Post changeVisiblePost(PostDTO.visiblePostRequestDTO request, Long userId, Long streamId);
}
