package com.umc5th.dayrecord.service.CommentService;

import com.umc5th.dayrecord.domain.Comment;
import org.springframework.data.domain.Slice;

public interface CommentQueryService {

    Slice<Comment> commentList(Long postId, Integer page);

    Boolean existById(Long commentId);
}
