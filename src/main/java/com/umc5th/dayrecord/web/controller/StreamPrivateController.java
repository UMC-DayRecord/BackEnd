package com.umc5th.dayrecord.web.controller;

import com.umc5th.dayrecord.apiPayload.ApiResponse;
import com.umc5th.dayrecord.converter.PostConverter;
import com.umc5th.dayrecord.converter.StreamConveter;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.domain.Stream;
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

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.domain.Slice;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Validated
@RequestMapping("/stream/private")
public class StreamPrivateController {
    
    private final StreamQueryService streamQueryService;
    private final PostQueryService postQueryService;
    
    @GetMapping("/search/{userId}")
    public ApiResponse<PostDTO.postSummaryListDTO> getPostList(@ExistUser @PathVariable(name = "userId") Long userId,
                                                               @CheckQuery @RequestParam(name = "query") String query,
                                                               @CheckPage @RequestParam(name = "page") Integer page) {
        Slice<Post> postList = streamQueryService.getSearchList(userId, query, page - 1);
        return ApiResponse.onSuccess(PostConverter.responsePost(postList, userId));
    }
    /**
     * 마이 스트림 생성 API
     * @param request StreamDTO.streamCreateRequestDTO;
     * @return ApiResponse
     */
    @PostMapping("/my")
    public ApiResponse<StreamDTO.streamDefaultDTO> postStreamCreate(@ExistUser @RequestParam(name = "userId") Long userId,
                                                               @CheckQuery @RequestParam(name = "streamName") String streamName) {
      
        StreamDTO.streamDefaultDTO request = StreamDTO.streamDefaultDTO.builder()
            .isPublic(false)
            .streamName(streamName)
            .userId(userId)
            .build();
        // userQueryService
        //     .getUser(request.getEmail(), request.getName())
        StreamDTO.streamDefaultDTO streamResponse = streamQueryService.insertStream(request);
            
        return ApiResponse.onSuccess(streamResponse);
    }
    
    
    /**
     * 마이스트림 조회 (카테고리 조회)
     * @param userId
     * @return
     */
    @GetMapping("/my")
    public ApiResponse<StreamDTO.streamSummaryListDTO> getStreamList(@ExistUser @RequestParam(name = "userId") Long userId,
                                                                        @CheckPage @RequestParam(name = "page") Integer page) {
      
        // 공개, 비공개를 가리지 않고 해당 유저의 스트림을 전부 조회함

        List<Stream> streamList =  streamQueryService.getStreamList(userId, page);
            
        return ApiResponse.onSuccess(StreamConveter.responseStream(streamList));
    }

    @DeleteMapping("/my/{streamId}")
    public ApiResponse<Long> deleteStream(@ExistUser @PathVariable(name = "streamId") Long streamId) {
      

        streamQueryService.deleteStream(streamId);
            
        return ApiResponse.onSuccess(streamId);
    }
    
    
    @GetMapping("/posts/{postId}")
    public ApiResponse<PostDTO.postDetailDTO> getPostDetail(@ExistPost @PathVariable(name = "postId") Long postId) {
        Post post = postQueryService.getPostDetailInfo(postId);
        return ApiResponse.onSuccess(PostConverter.detailPost(post));
    }
}
