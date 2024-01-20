package com.umc5th.dayrecord.web.controller;

import com.umc5th.dayrecord.apiPayload.ApiResponse;
import com.umc5th.dayrecord.service.CommentService.CommentCommandService;
import com.umc5th.dayrecord.web.dto.CommentDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Validated
@RequestMapping("/comments")
public class CommentController {

    private final CommentCommandService commentCommandService;

    @PostMapping("/{postId}")
    public ApiResponse<CommentDTO.commentResponseDTO> writeComment(@PathVariable(name = "postId") Long postId,
                                                                   CommentDTO.commentRequestDTO request) {
        return ApiResponse.onSuccess(commentCommandService.createComment(request, postId));
    }
}
