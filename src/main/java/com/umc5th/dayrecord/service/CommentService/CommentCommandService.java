package com.umc5th.dayrecord.service.CommentService;

import com.umc5th.dayrecord.domain.Comment;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.web.dto.CommentDTO;

public interface CommentCommandService {

    Comment createComment(CommentDTO.commentRequestDTO request, Long postId);

    Post removeComment(Long commentId);

    Comment updateComment(CommentDTO.editCommentRequestDTO request, Long commentId);
}
