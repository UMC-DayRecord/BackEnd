package com.umc5th.dayrecord.service.CommentService;

import com.umc5th.dayrecord.domain.Comment;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.domain.User;
import com.umc5th.dayrecord.web.dto.CommentDTO;

public interface CommentCommandService {

    Comment createComment(CommentDTO.commentRequestDTO request, Long postId, User user);

    Integer removeComment(Long commentId, User user);

    Comment updateComment(CommentDTO.commentRequestDTO request, Long commentId, User user);
}
