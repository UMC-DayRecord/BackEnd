package com.umc5th.dayrecord.web.controller;

import com.umc5th.dayrecord.apiPayload.ApiResponse;
import com.umc5th.dayrecord.apiPayload.code.status.ErrorStatus;
import com.umc5th.dayrecord.apiPayload.exception.handler.UserNotFoundHandler;
import com.umc5th.dayrecord.converter.CommentConverter;
import com.umc5th.dayrecord.domain.Comment;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.domain.User;
import com.umc5th.dayrecord.service.CommentService.CommentCommandService;
import com.umc5th.dayrecord.service.CommentService.CommentQueryService;
import com.umc5th.dayrecord.service.UserQueryService;
import com.umc5th.dayrecord.validation.annotation.CheckPage;
import com.umc5th.dayrecord.validation.annotation.ExistComment;
import com.umc5th.dayrecord.validation.annotation.ExistPost;
import com.umc5th.dayrecord.validation.annotation.ExistUser;
import com.umc5th.dayrecord.web.dto.CommentDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Validated
@RequestMapping("/comments")
public class CommentController {

    private final CommentCommandService commentCommandService;
    private final CommentQueryService commentQueryService;
    private final UserQueryService userQueryService;

    /**
     * 댓글 생성 API
     * @param postId
     * @param request
     * @return CommentDTO.commentResponseDTO
     * jwt 토큰 적용
     */
    @PostMapping("/{postId}")
    public ApiResponse<CommentDTO.commentResponseDTO> postComment(@ExistPost @PathVariable(name = "postId") Long postId,
                                                                   @Valid @RequestBody CommentDTO.commentRequestDTO request) {
        // 현재 로그인한 사용자의 사용자 정보 조회
        String loggedInUserNickName = userQueryService.getLoggedInUserNickName()
                .orElseThrow(() -> new UserNotFoundHandler(ErrorStatus._UNAUTHORIZED));

        User user = userQueryService.getUser(loggedInUserNickName)
                .orElseThrow(() -> new UserNotFoundHandler(ErrorStatus._USER_NOT_FOUND));

        Comment comment = commentCommandService.createComment(request, postId, user);
        return ApiResponse.onSuccess(CommentConverter.responseComment(comment, user.getId()));
    }

    /**
     * 댓글 조회 API
     * @param postId
     * @param userId
     * @param page
     * @return CommentDTO.commentListDTO -> CommentDTO.commentResponseDTO 리스트
     */
    /*@GetMapping("/{postId}/list/{userId}")
    public ApiResponse<CommentDTO.commentListDTO> getCommentList(@ExistPost @PathVariable(name = "postId") Long postId,
                                                                 @ExistUser @PathVariable(name = "userId") Long userId,
                                                                 @CheckPage @RequestParam(name = "page") Integer page) {
        Slice<Comment> commentList = commentQueryService.commentList(postId, page-1);
        return ApiResponse.onSuccess(CommentConverter.getComments(commentList, userId));
    }*/

    // jwt 토큰 적용
    @GetMapping("/list/{postId}")
    public ApiResponse<CommentDTO.commentListDTO> getCommentList(@ExistPost @PathVariable(name = "postId") Long postId,
                                                                 @CheckPage @RequestParam(name = "page") Integer page) {
        // 현재 로그인한 사용자의 사용자 정보 조회
        String loggedInUserNickName = userQueryService.getLoggedInUserNickName()
                .orElseThrow(() -> new UserNotFoundHandler(ErrorStatus._UNAUTHORIZED));

        User user = userQueryService.getUser(loggedInUserNickName)
                .orElseThrow(() -> new UserNotFoundHandler(ErrorStatus._USER_NOT_FOUND));

        Slice<Comment> commentList = commentQueryService.commentList(postId, page-1);
        return ApiResponse.onSuccess(CommentConverter.getComments(commentList, user.getId()));
    }

    /**
     * 댓글 삭제 API
     * @param commentId
     * @return CommentDTO.commentSizeDTO(댓글 수)
     * jwt 토큰 적용
     */
    @DeleteMapping("/{commentId}")
    public ApiResponse<CommentDTO.commentSizeDTO> deleteComment(@ExistComment @PathVariable(name = "commentId") Long commentId) {
        // 현재 로그인한 사용자의 사용자 정보 조회
        String loggedInUserNickName = userQueryService.getLoggedInUserNickName()
                .orElseThrow(() -> new UserNotFoundHandler(ErrorStatus._UNAUTHORIZED));

        User user = userQueryService.getUser(loggedInUserNickName)
                .orElseThrow(() -> new UserNotFoundHandler(ErrorStatus._USER_NOT_FOUND));

        Integer commentSize = commentCommandService.removeComment(commentId, user);
        return ApiResponse.onSuccess(CommentConverter.commentSize(commentSize));
    }

    /**
     * 댓글 수정 API
     * @param commentId
     * @param request
     * @return CommentDTO.commentResponseDTO
     * jwt 토큰 적용
     */
    @PutMapping("/{commentId}")
    public ApiResponse<CommentDTO.commentResponseDTO> changeComment(@ExistComment @PathVariable(name = "commentId") Long commentId,
                                                                    @Valid @RequestBody CommentDTO.commentRequestDTO request) {
        // 현재 로그인한 사용자의 사용자 정보 조회
        String loggedInUserNickName = userQueryService.getLoggedInUserNickName()
                .orElseThrow(() -> new UserNotFoundHandler(ErrorStatus._UNAUTHORIZED));

        User user = userQueryService.getUser(loggedInUserNickName)
                .orElseThrow(() -> new UserNotFoundHandler(ErrorStatus._USER_NOT_FOUND));

        Comment comment = commentCommandService.updateComment(request, commentId, user);
        return ApiResponse.onSuccess(CommentConverter.responseComment(comment, user.getId()));
    }
}
