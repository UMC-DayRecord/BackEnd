package com.umc5th.dayrecord.service.LikesService;

public interface LikesCommandService {

    void updateLikes(Long postId, Long userId);

    Boolean likeCheck(Long postId, Long userId);
}
