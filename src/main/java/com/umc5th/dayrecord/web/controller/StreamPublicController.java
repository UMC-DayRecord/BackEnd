package com.umc5th.dayrecord.web.controller;

import com.umc5th.dayrecord.apiPayload.ApiResponse;
import com.umc5th.dayrecord.converter.PostConverter;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.service.PostService.PostQueryService;
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
    public ApiResponse<PostDTO.postListDTO> getPostList(@ExistUser @PathVariable(name = "userId") Long userId,
                                                        @RequestParam(name = "page") Integer page) {

        Slice<Post> postList = postQueryService.getPostList(userId, page-1);
        return ApiResponse.onSuccess(PostConverter.responsePost(postList));
    }
}
