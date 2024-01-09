package com.umc5th.dayrecord.web.controller;

import com.umc5th.dayrecord.apiPayload.ApiResponse;
import com.umc5th.dayrecord.converter.PostConverter;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.service.PostService.PostQueryService;
import com.umc5th.dayrecord.web.dto.PostDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/stream/public")
public class StreamPublicController {

    private final PostQueryService postQueryService;

    @GetMapping("/{userId}")
    public ApiResponse<PostDTO.postListDTO> getPostList(@PathVariable(name = "userId") Long userId,
                                                        @RequestParam(name = "page") Integer page) {

        Slice<Post> postList = postQueryService.getPostList(userId, page);
        return ApiResponse.onSuccess(PostConverter.responsePost(postList));
    }
}
