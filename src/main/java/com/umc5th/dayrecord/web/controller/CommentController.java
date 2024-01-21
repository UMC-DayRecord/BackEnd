package com.umc5th.dayrecord.web.controller;

import com.umc5th.dayrecord.apiPayload.ApiResponse;
import com.umc5th.dayrecord.converter.CommentConverter;
import com.umc5th.dayrecord.domain.Comment;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.service.CommentService.CommentCommandService;
import com.umc5th.dayrecord.service.CommentService.CommentQueryService;
import com.umc5th.dayrecord.validation.annotation.ExistComment;
import com.umc5th.dayrecord.validation.annotation.ExistPost;
import com.umc5th.dayrecord.validation.annotation.ExistUser;
import com.umc5th.dayrecord.web.dto.CommentDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Validated
@RequestMapping("/comments")
public class CommentController {

    private final CommentCommandService commentCommandService;
    private final CommentQueryService commentQueryService;

    @PostMapping("/{postId}")
    public ApiResponse<CommentDTO.commentResponseDTO> postComment(@ExistPost @PathVariable(name = "postId") Long postId,
                                                                   @Valid @RequestBody CommentDTO.commentRequestDTO request) {
        return ApiResponse.onSuccess(commentCommandService.createComment(request, postId));
    }

    @GetMapping("/{postId}/list/{userId}")
    public ApiResponse<CommentDTO.commentListDTO> getCommentList(@ExistPost @PathVariable(name = "postId") Long postId,
                                                                 @ExistUser @PathVariable(name = "userId") Long userId) {
        List<Comment> commentList = commentQueryService.commentList(postId);
        return ApiResponse.onSuccess(CommentConverter.getComments(commentList, userId));
    }

    @DeleteMapping("/{commentId}")
    public ApiResponse<CommentDTO.commentSizeDTO> deleteComment(@ExistComment @PathVariable(name = "commentId") Long commentId) {
        Post post = commentCommandService.removeComment(commentId);
        return ApiResponse.onSuccess(CommentConverter.commentSize(post));
    }

    @PutMapping("/{commentId}")
    public ApiResponse<CommentDTO.commentResponseDTO> changeComment(@ExistComment @PathVariable(name = "commentId") Long commentId,
                                                                    @Valid @RequestBody CommentDTO.editCommentRequestDTO request) {
        Comment comment = commentCommandService.updateComment(request, commentId);
        return ApiResponse.onSuccess(CommentConverter.responseComment(comment, request.getUserId()));
    }
}
