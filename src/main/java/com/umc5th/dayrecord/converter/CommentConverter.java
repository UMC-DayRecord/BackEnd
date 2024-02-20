package com.umc5th.dayrecord.converter;

import com.umc5th.dayrecord.domain.Comment;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.domain.User;
import com.umc5th.dayrecord.web.dto.CommentDTO;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.stream.Collectors;

public class CommentConverter {

    public static Comment saveComment(CommentDTO.commentRequestDTO requestDTO, Post post, User user) {
        return Comment.builder()
                .detail(requestDTO.getDetail())
                .post(post)
                .user(user)
                .build();
    }

    public static CommentDTO.commentResponseDTO responseComment(Comment comment, Long userId) {
        boolean isAuthor = comment.getUser().getId().equals(userId);

        String profilePhotoUrl = "";
        if (comment.getUser().getUserPhoto() != null) {
            profilePhotoUrl = comment.getUser().getUserPhoto().getUrl();
        }

        return CommentDTO.commentResponseDTO.builder()
                .commentId(comment.getId())
                .nickname(comment.getUser().getNickname())
                .detail(comment.getDetail())
                .isAuthor(isAuthor)
                .profilePhoto(profilePhotoUrl)
                .build();
    }

    public static CommentDTO.commentListDTO getComments(Slice<Comment> list, Long userId) {
        List<CommentDTO.commentResponseDTO> commentList = list.stream()
                .map((Comment comment) -> responseComment(comment, userId))
                .collect(Collectors.toList());

        return CommentDTO.commentListDTO.builder()
                .commentList(commentList)
                .listSize(list.getNumberOfElements())
                .hasNext(list.hasNext())
                .isFirst(list.isFirst())
                .isLast(list.isLast())
                .build();
    }

    public static CommentDTO.commentSizeDTO commentSize(Integer commentSize) {
        return CommentDTO.commentSizeDTO.builder()
                .commentSize(Long.valueOf(commentSize))
                .build();
    }
}
