package com.umc5th.dayrecord.service.CommentService;

import com.umc5th.dayrecord.domain.Comment;
import com.umc5th.dayrecord.web.dto.CommentDTO;

public interface CommentCommandService {

    CommentDTO.commentResponseDTO createComment(CommentDTO.commentRequestDTO request, Long postId);

    void removeComment(Long commentId);

    Comment updateComment(CommentDTO.editCommentRequestDTO request, Long commentId);
}
