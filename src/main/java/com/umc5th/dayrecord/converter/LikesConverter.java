package com.umc5th.dayrecord.converter;

import com.umc5th.dayrecord.domain.Likes;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.domain.User;
import com.umc5th.dayrecord.web.dto.LikesDTO;

public class LikesConverter {
    public static LikesDTO.likeResponseDTO likeResult(Post post, boolean status) {
        return LikesDTO.likeResponseDTO.builder()
                .postId(post.getId())
                .likes(Long.valueOf(post.getLikesList().size()))
                .like_check(status)
                .build();
    }

    public static Likes createLike(Post post, User user) {
        return Likes.builder()
                .post(post)
                .user(user)
                .build();
    }
}
