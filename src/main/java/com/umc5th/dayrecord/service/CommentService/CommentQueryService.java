package com.umc5th.dayrecord.service.CommentService;

import com.umc5th.dayrecord.domain.Comment;

import java.util.List;

public interface CommentQueryService {

    List<Comment> commentList(Long postId);

    Boolean existById(Long commentId);
}
