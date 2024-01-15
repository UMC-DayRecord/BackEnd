package com.umc5th.dayrecord.converter;

import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.domain.PostPhoto;
import com.umc5th.dayrecord.web.dto.PostDTO;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.stream.Collectors;

public class PostConverter {

    public static PostDTO.postSummaryDTO fetchPost(Post post, Long userId) {
        boolean like_check = post.getLikesList().stream()
                .anyMatch(like -> like != null && like.getUser().getId().equals(userId));

        return PostDTO.postSummaryDTO.builder()
                .postId(post.getId())
                .nickname(post.getUser().getNickname())
                .postImg(post.getPostPhotoList().stream()
                        .map(PostPhoto::getUrl)
                        .limit(3) // 최대 3개까지만 가져오기
                        .collect(Collectors.toList()))
                .likes(Long.valueOf(post.getLikesList().size()))
                .isLike(like_check)
                .comments(Long.valueOf(post.getCommentList().size()))
                .createdAt(post.getCreatedAt())
                .build();
    }

    public static PostDTO.postSummaryListDTO responsePost(Slice<Post> postList, Long userId) {
        List<PostDTO.postSummaryDTO> postDTOList = postList.stream()
                .map((Post post) -> fetchPost(post, userId))
                .collect(Collectors.toList());

        return PostDTO.postSummaryListDTO.builder()
                .postList(postDTOList)
                .listSize(postList.getNumberOfElements())
                .hasNext(postList.hasNext())
                .isFirst(postList.isFirst())
                .isLast(postList.isLast())
                .build();
    }

    public static PostDTO.postDetailDTO detailPost(Post post) {
        return PostDTO.postDetailDTO.builder()
                .postId(post.getId())
                .streamName(post.getStream().getStreamName())
                .postImg(post.getPostPhotoList().stream()
                        .map(PostPhoto::getUrl)
                        .collect(Collectors.toList()))
                .detail(post.getDetail())
                .likes(Long.valueOf(post.getLikesList().size()))
                .comments(Long.valueOf(post.getCommentList().size()))
                .createdAt(post.getCreatedAt())
    }
}
