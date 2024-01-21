package com.umc5th.dayrecord.converter;

import com.umc5th.dayrecord.domain.Comment;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.domain.User;
import com.umc5th.dayrecord.web.dto.CommentDTO;

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

        return CommentDTO.commentResponseDTO.builder()
                .commentId(comment.getId())
                .nickname(comment.getUser().getNickname())
                .detail(comment.getDetail())
                .isAuthor(isAuthor)
                .build();
    }

    public static CommentDTO.commentListDTO getComments(List<Comment> list, Long userId) {
        List<CommentDTO.commentResponseDTO> commentList = list.stream()
                .map((Comment comment) -> responseComment(comment, userId))
                .collect(Collectors.toList());
        return CommentDTO.commentListDTO.builder()
                .commentList(commentList)
                .listSize(Long.valueOf(list.size()))
                .build();
    }

    public static CommentDTO.commentSizeDTO commentSize(Post post) {
        return CommentDTO.commentSizeDTO.builder()
                .commentSize(Long.valueOf(post.getCommentList().size()))
                .build();
    }
}
