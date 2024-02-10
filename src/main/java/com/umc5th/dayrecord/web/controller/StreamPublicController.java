package com.umc5th.dayrecord.web.controller;

import com.umc5th.dayrecord.apiPayload.ApiResponse;
import com.umc5th.dayrecord.apiPayload.code.status.ErrorStatus;
import com.umc5th.dayrecord.apiPayload.exception.handler.UserNotFoundHandler;
import com.umc5th.dayrecord.converter.PostConverter;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.domain.Stream;
import com.umc5th.dayrecord.domain.User;
import com.umc5th.dayrecord.service.PostService.PostQueryService;
import com.umc5th.dayrecord.service.StreamService.StreamQueryService;
import com.umc5th.dayrecord.service.UserQueryService;
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
@RequestMapping("/stream/public")
public class StreamPublicController {
    private final PostQueryService postQueryService;
    private final UserQueryService userQueryService;

    /*@GetMapping("/{userId}")
    public ApiResponse<PostDTO.postSummaryListDTO> getPostList(@ExistUser @PathVariable(name = "userId") Long userId,
                                                        @CheckPage @RequestParam(name = "page") Integer page) {

        Slice<Post> postList = postQueryService.getPostList(userId, page-1);
        return ApiResponse.onSuccess(PostConverter.responsePost(postList, userId));
    }*/

    // jwt 토큰 적용
    @GetMapping("")
    public ApiResponse<PostDTO.postSummaryListDTO> getPostList(@CheckPage @RequestParam(name = "page") Integer page) {
        // 현재 로그인한 사용자의 사용자 정보 조회
        String loggedInUserNickName = userQueryService.getLoggedInUserNickName()
                .orElseThrow(() -> new UserNotFoundHandler(ErrorStatus._UNAUTHORIZED));

        User user = userQueryService.getUser(loggedInUserNickName)
                .orElseThrow(() -> new UserNotFoundHandler(ErrorStatus._USER_NOT_FOUND));

        Slice<Post> postList = postQueryService.getPostList(user, page-1);
        return ApiResponse.onSuccess(PostConverter.responsePost(postList, user.getId()));
    }

    /*@GetMapping("/search/{userId}")
    public ApiResponse<PostDTO.postSummaryListDTO> getPostList(@ExistUser @PathVariable(name = "userId") Long userId,
                                                               @CheckQuery @RequestParam(name = "query") String query,
                                                               @CheckPage @RequestParam(name = "page") Integer page) {
        Slice<Post> postList = postQueryService.getSearchList(userId, query, page - 1);
        return ApiResponse.onSuccess(PostConverter.responsePost(postList, userId));
    }*/

    // jwt 토큰 적용
    @GetMapping("/search")
    public ApiResponse<PostDTO.postSummaryListDTO> getPostList(@CheckQuery @RequestParam(name = "query") String query,
                                                               @CheckPage @RequestParam(name = "page") Integer page) {
        // 현재 로그인한 사용자의 사용자 정보 조회
        String loggedInUserNickName = userQueryService.getLoggedInUserNickName()
                .orElseThrow(() -> new UserNotFoundHandler(ErrorStatus._UNAUTHORIZED));

        User user = userQueryService.getUser(loggedInUserNickName)
                .orElseThrow(() -> new UserNotFoundHandler(ErrorStatus._USER_NOT_FOUND));

        Slice<Post> postList = postQueryService.getSearchList(user.getId(), query, page - 1);
        return ApiResponse.onSuccess(PostConverter.responsePost(postList, user.getId()));
    }

    @GetMapping("/posts/{postId}")
    public ApiResponse<PostDTO.postDetailDTO> getPostDetail(@ExistPost @PathVariable(name = "postId") Long postId) {
        Post post = postQueryService.getPostDetailInfo(postId);
        return ApiResponse.onSuccess(PostConverter.detailPost(post));
    }
    
}
