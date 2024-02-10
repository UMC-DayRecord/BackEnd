package com.umc5th.dayrecord.service.LikesService;

import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.domain.User;

public interface LikesCommandService {

    Boolean updateLikes(Long postId, User user);

    Boolean likeCheck(Post post, User user);
}
