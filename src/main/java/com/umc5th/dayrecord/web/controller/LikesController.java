package com.umc5th.dayrecord.web.controller;

import com.umc5th.dayrecord.apiPayload.ApiResponse;
import com.umc5th.dayrecord.converter.LikesConverter;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.service.LikesService.LikesCommandService;
import com.umc5th.dayrecord.service.PostService.PostQueryService;
import com.umc5th.dayrecord.validation.annotation.ExistPost;
import com.umc5th.dayrecord.validation.annotation.ExistUser;
import com.umc5th.dayrecord.web.dto.LikesDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Validated
public class LikesController {

    private final PostQueryService postQueryService;
    private final LikesCommandService likesCommandService;

    @PostMapping("/{postId}/likes/{userId}")
    public ApiResponse<LikesDTO.likeResponseDTO> likePost(@ExistPost @PathVariable(name = "postId") Long postId,
                                                          @ExistUser @PathVariable(name = "userId") Long userId) {
        Boolean isLike = likesCommandService.updateLikes(postId, userId);
        Post post = postQueryService.getPost(postId);
        return ApiResponse.onSuccess(LikesConverter.likeResult(post, isLike));
    }
}
