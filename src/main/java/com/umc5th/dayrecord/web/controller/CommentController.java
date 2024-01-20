package com.umc5th.dayrecord.web.controller;

import com.umc5th.dayrecord.apiPayload.ApiResponse;
import com.umc5th.dayrecord.converter.CommentConverter;
import com.umc5th.dayrecord.domain.Comment;
import com.umc5th.dayrecord.service.CommentService.CommentCommandService;
import com.umc5th.dayrecord.service.CommentService.CommentQueryService;
import com.umc5th.dayrecord.web.dto.CommentDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Validated
@RequestMapping("/comments")
public class CommentController {

    private final CommentCommandService commentCommandService;
    private final CommentQueryService commentQueryService;

    @PostMapping("/{postId}")
    public ApiResponse<CommentDTO.commentResponseDTO> postComment(@PathVariable(name = "postId") Long postId,
                                                                   @RequestBody CommentDTO.commentRequestDTO request) {
        return ApiResponse.onSuccess(commentCommandService.createComment(request, postId));
    }

    @GetMapping("/{postId}")
    public ApiResponse<CommentDTO.commentListDTO> getCommentList(@PathVariable(name = "postId") Long postId) {
        List<Comment> commentList = commentQueryService.commentList(postId);
        return ApiResponse.onSuccess(CommentConverter.getComments(commentList));
    }

    @DeleteMapping("/{commentId}")
    public ApiResponse<String> deleteComment(@PathVariable(name = "commentId") Long commentId) {
        commentCommandService.removeComment(commentId);
        return ApiResponse.onSuccess("성공했습니다.");
    }

    @PutMapping("/{commentId}")
    public ApiResponse<CommentDTO.commentResponseDTO> changeComment(@PathVariable(name = "commentId") Long commentId,
                                                                    @RequestBody CommentDTO.editCommentRequestDTO request) {
        Comment comment = commentCommandService.updateComment(request, commentId);
        return ApiResponse.onSuccess(CommentConverter.responseComment(comment));
    }
}
