package com.umc5th.dayrecord.web.controller;

import com.umc5th.dayrecord.apiPayload.ApiResponse;
import com.umc5th.dayrecord.converter.LikesConverter;
import com.umc5th.dayrecord.converter.PostConverter;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.service.LikesService.LikesCommandService;
import com.umc5th.dayrecord.service.PostService.PostQueryService;
import com.umc5th.dayrecord.validation.annotation.CheckPage;
import com.umc5th.dayrecord.validation.annotation.ExistUser;
import com.umc5th.dayrecord.web.dto.LikesDTO;
import com.umc5th.dayrecord.web.dto.PostDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Validated
@RequestMapping("/stream/public")
public class StreamPublicController {

    private final PostQueryService postQueryService;
    private final LikesCommandService likesCommandService;

    @GetMapping("/{userId}")
    public ApiResponse<PostDTO.postSummaryListDTO> getPostList(@ExistUser @PathVariable(name = "userId") Long userId,
                                                        @CheckPage @RequestParam(name = "page") Integer page) {

        Slice<Post> postList = postQueryService.getPostList(userId, page-1);
        return ApiResponse.onSuccess(PostConverter.responsePost(postList, userId));
    }

    @PostMapping("/{postId}/like/{userId}")
    public ApiResponse<LikesDTO.likeResponseDTO> likePost(@PathVariable(name = "postId") Long postId,
                                                          @ExistUser @PathVariable(name = "userId") Long userId) {
        likesCommandService.updateLikes(postId, userId);
        Boolean isLike = likesCommandService.likeCheck(postId, userId);
        Post post = postQueryService.getPost(postId);
        return ApiResponse.onSuccess(LikesConverter.likeResult(post, isLike));
    }
}
