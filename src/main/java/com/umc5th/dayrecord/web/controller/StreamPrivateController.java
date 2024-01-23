package com.umc5th.dayrecord.web.controller;

import com.umc5th.dayrecord.apiPayload.ApiResponse;
import com.umc5th.dayrecord.converter.PostConverter;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.service.PostService.PostQueryService;
import com.umc5th.dayrecord.service.StreamService.StreamQueryService;
import com.umc5th.dayrecord.validation.annotation.CheckPage;
import com.umc5th.dayrecord.validation.annotation.CheckQuery;
import com.umc5th.dayrecord.validation.annotation.ExistPost;
import com.umc5th.dayrecord.validation.annotation.ExistUser;
import com.umc5th.dayrecord.web.dto.PostDTO;
import com.umc5th.dayrecord.web.dto.StreamDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Slice;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Validated
@RequestMapping("/stream/private")
public class StreamPrivateController {
    
    private final StreamQueryService streamQueryService;
    /**
     * 마이 스트림 생성 API
     * @param request StreamDTO.streamCreateRequestDTO;
     * @return ApiResponse
     */
    @PostMapping("/")
    public ApiResponse<StreamDTO.streamResponseDTO> postStreamCreate(@ExistUser @RequestParam(name = "userId") Long userId,
                                                               @CheckQuery @RequestParam(name = "streamName") String streamName) {
      
        StreamDTO.streamCreateDTO request = StreamDTO.streamCreateDTO.builder()
            .isPublic(false)
            .streamName(streamName)
            .userId(userId)
            .build();
        // userQueryService
        //     .getUser(request.getEmail(), request.getName())
        StreamDTO.streamResponseDTO streamResponse = streamQueryService.insertStream(request);
            
        return ApiResponse.onSuccess(streamResponse);
    }
    private final PostQueryService postQueryService;
    @GetMapping("/user/{userId}")
    public ApiResponse<PostDTO.postSummaryListDTO> getPostList(@ExistUser @PathVariable(name = "userId") Long userId,
                                                        @CheckPage @RequestParam(name = "page") Integer page) {

        Slice<Post> postList = postQueryService.getPostList(userId, page-1);
        return ApiResponse.onSuccess(PostConverter.responsePost(postList, userId));
    }
}
