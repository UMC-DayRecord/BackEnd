package com.umc5th.dayrecord.converter;

import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.domain.PostPhoto;
import com.umc5th.dayrecord.web.dto.PostDTO;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.stream.Collectors;

public class PostConverter {

    public static PostDTO.postDTO fetchPost(Post post) {
        return PostDTO.postDTO.builder()
                .postId(post.getId())
                .nickname(post.getUser().getNickname())
                .postImg(post.getPostPhotoList().stream()
                        .map(PostPhoto::getUrl)
                        .limit(3) // 최대 3개까지만 가져오기
                        .collect(Collectors.toList()))
                .likes(Long.valueOf(post.getLikesList().size()))
                .comments(Long.valueOf(post.getCommentList().size()))
                .createdAt(post.getCreatedAt())
                .build();
    }

    public static PostDTO.postListDTO responsePost(Slice<Post> postList) {
        List<PostDTO.postDTO> postDTOList = postList.stream()
                .map(PostConverter::fetchPost)
                .collect(Collectors.toList());

        return PostDTO.postListDTO.builder()
                .postList(postDTOList)
                .listSize(postList.getContent().size())
                .hasNext(postList.hasNext())
                .isFirst(postList.isFirst())
                .isLast(postList.isLast())
                .build();
    }
}
