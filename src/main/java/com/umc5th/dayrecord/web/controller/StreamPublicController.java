package com.umc5th.dayrecord.web.controller;

import com.umc5th.dayrecord.apiPayload.ApiResponse;
import com.umc5th.dayrecord.converter.PostConverter;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.service.PostService.PostQueryService;
import com.umc5th.dayrecord.validation.annotation.CheckPage;
import com.umc5th.dayrecord.validation.annotation.CheckQuery;
import com.umc5th.dayrecord.validation.annotation.ExistPost;
import com.umc5th.dayrecord.validation.annotation.ExistUser;
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

    @GetMapping("/{userId}")
    public ApiResponse<PostDTO.postSummaryListDTO> getPostList(@ExistUser @PathVariable(name = "userId") Long userId,
                                                        @CheckPage @RequestParam(name = "page") Integer page) {

        Slice<Post> postList = postQueryService.getPostList(userId, page-1);
        return ApiResponse.onSuccess(PostConverter.responsePost(postList, userId));
    }

    @GetMapping("/search/{userId}")
    public ApiResponse<PostDTO.postSummaryListDTO> getPostList(@ExistUser @PathVariable(name = "userId") Long userId,
                                                               @CheckQuery @RequestParam(name = "query") String query,
                                                               @CheckPage @RequestParam(name = "page") Integer page) {
        Slice<Post> postList = postQueryService.getSearchList(userId, query, page - 1);
        return ApiResponse.onSuccess(PostConverter.responsePost(postList, userId));
    }

    @GetMapping("/posts/{postId}")
    public ApiResponse<PostDTO.postDetailDTO> getPostDetail(@ExistPost @PathVariable(name = "postId") Long postId) {
        Post post = postQueryService.getPostDetailInfo(postId);
        return ApiResponse.onSuccess(PostConverter.detailPost(post));
    }
}
