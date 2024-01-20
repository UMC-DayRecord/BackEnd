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

    public static CommentDTO.commentResponseDTO responseComment(Comment comment) {
        return CommentDTO.commentResponseDTO.builder()
                .commentId(comment.getId())
                .nickname(comment.getUser().getNickname())
                .detail(comment.getDetail())
                .build();
    }

    public static CommentDTO.commentListDTO getComments(List<Comment> list) {
        List<CommentDTO.commentResponseDTO> commentList = list.stream()
                .map(CommentConverter::responseComment)
                .collect(Collectors.toList());
        return CommentDTO.commentListDTO.builder()
                .commentList(commentList)
                .listSize(Long.valueOf(list.size()))
                .build();
    }
}
