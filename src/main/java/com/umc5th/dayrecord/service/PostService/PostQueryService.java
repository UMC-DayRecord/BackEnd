package com.umc5th.dayrecord.service.PostService;

import com.umc5th.dayrecord.domain.Post;
import org.springframework.data.domain.Slice;

public interface PostQueryService {

    Slice<Post> getPostList(String nickname, Integer page);
}
