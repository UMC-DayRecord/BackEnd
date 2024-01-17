package com.umc5th.dayrecord.service.PostService;

import com.umc5th.dayrecord.domain.Post;
import org.springframework.data.domain.Slice;

public interface PostQueryService {

    Slice<Post> getPostList(Long userId, Integer page);

<<<<<<< HEAD
    Post getPost(Long postId);
=======
    Slice<Post> getSearchList(Long userId, String query, Integer page);
    Post getPostDetailInfo(Long postId);
    Boolean existById(Long postId);
>>>>>>> 708a1d274281042ee40a2c96ec377ab0d7928f96
}
