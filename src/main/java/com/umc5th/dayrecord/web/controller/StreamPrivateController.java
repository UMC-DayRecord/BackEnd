package com.umc5th.dayrecord.web.controller;

import com.umc5th.dayrecord.apiPayload.ApiResponse;
import com.umc5th.dayrecord.converter.DiaryConverter;
import com.umc5th.dayrecord.converter.PostConverter;
import com.umc5th.dayrecord.converter.StreamConveter;
import com.umc5th.dayrecord.domain.Diary;
import com.umc5th.dayrecord.domain.DiaryPhoto;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.domain.Stream;
import com.umc5th.dayrecord.service.DiaryService.DiaryQueryService;
import com.umc5th.dayrecord.service.PostService.PostQueryService;
import com.umc5th.dayrecord.service.StreamService.StreamQueryService;
import com.umc5th.dayrecord.validation.annotation.CheckName;
import com.umc5th.dayrecord.validation.annotation.CheckPage;
import com.umc5th.dayrecord.validation.annotation.CheckQuery;
import com.umc5th.dayrecord.validation.annotation.ExistDiary;
import com.umc5th.dayrecord.validation.annotation.ExistPost;
import com.umc5th.dayrecord.validation.annotation.ExistStream;
import com.umc5th.dayrecord.validation.annotation.ExistUser;
import com.umc5th.dayrecord.web.dto.DiaryDTO;
import com.umc5th.dayrecord.web.dto.PostDTO;
import com.umc5th.dayrecord.web.dto.StreamDTO;
import com.umc5th.dayrecord.web.dto.PostDTO.postSummaryListDTO;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.data.domain.Slice;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Validated
@RequestMapping("/stream/private")
public class StreamPrivateController {
    
    private final StreamQueryService streamQueryService;
    private final PostQueryService postQueryService;
    private final DiaryQueryService diaryQueryService;
    
    /**
     *  마이스트림 메인화면
     * @param streamId
     * @param userId
     * @param page
     * @return
     */
    @GetMapping("/my/{streamId}")
    public ApiResponse<postSummaryListDTO> getStreamPostList(@ExistStream @PathVariable(name = "streamId") Long streamId,
                                                       @ExistUser @RequestParam(name = "userId") Long userId,
                                                       @CheckPage @RequestParam(name = "page") Integer page) {
        Slice<Post> postList = streamQueryService.getStreamPostList(userId, streamId, page - 1);
        return ApiResponse.onSuccess(PostConverter.responsePost(postList, userId));
    }

    /**
     * 일기보드 화면 출력 API 
     * @param streamId
     * @param userId
     * @param page
     * @return
     */
    @GetMapping("/daliyBoard/{streamId}")
    public ApiResponse<postSummaryListDTO> getdaliyBoardList(@ExistStream @PathVariable(name = "streamId") Long streamId,
                                                       @ExistUser @RequestParam(name = "userId") Long userId,
                                                       @CheckPage @RequestParam(name = "page") Integer page) {
        Slice<Post> postList = streamQueryService.getDaliyBoardList(userId, streamId, page - 1);
        return ApiResponse.onSuccess(PostConverter.responsePost(postList, userId));
    }
    /**
     * 마이스트림 검색
     * post detail 과 stream name을 검색함
     * 
     * @param userId
     * @param query
     * @param page
     * @return ApiResponse
     */
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
                                                               @CheckName @RequestParam(name = "streamName") String streamName) {
      
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
     * @return ApiResponse
     */
    @GetMapping("/my")
    public ApiResponse<StreamDTO.streamSummaryListDTO> getStreamList(@ExistUser @RequestParam(name = "userId") Long userId,
                                                                        @CheckPage @RequestParam(name = "page") Integer page) {
      
        // 공개, 비공개를 가리지 않고 해당 유저의 스트림을 전부 조회함

        List<Stream> streamList =  streamQueryService.getStreamList(userId, page);
            
        return ApiResponse.onSuccess(StreamConverter.responseStream(streamList));
    }
    //stream/private/my/{uesrId}/{streamId}
    @GetMapping("/my/{uesrId}/{streamId}")
    public ApiResponse<PostDTO.postSummaryListDTO> getPostList(@ExistUser @PathVariable(name = "userId") Long userId,
                                                            @ExistStream @PathVariable(name = "streamId") Long streamId,
                                                        @CheckPage @RequestParam(name = "page") Integer page) {

        Slice<Post> postList = streamQueryService.getStreamPostList(userId, streamId, page-1);
        return ApiResponse.onSuccess(PostConverter.responsePost(postList, userId));
    }


    /**
     * 마이스트림 삭제 API
     * @param streamId
     * @return ApiResponse
     */
    @DeleteMapping("/my/{streamId}")
    public ApiResponse<Long> deleteStream(@ExistStream @PathVariable(name = "streamId") Long streamId) {
      
        streamQueryService.deleteStream(streamId);
            
        return ApiResponse.onSuccess(streamId);
    }


     /**
      * 마이스트림 일기 삭제 API
      * @param postId
      * @return ApiResponse
      */
    @DeleteMapping("/my/post/{postId}")
    public ApiResponse<Long> deletePost(@ExistPost @PathVariable(name = "postId") Long postId) {
      
        postQueryService.deletePost(postId);
            
        return ApiResponse.onSuccess(postId);
    }
    
    


    
    /**
     * 
     * @param postId
     * @return ApiResponse
     */
    @GetMapping("/posts/{postId}")
    public ApiResponse<PostDTO.postDetailDTO> getPostDetail(@ExistPost @PathVariable(name = "postId") Long postId) {
        Post post = postQueryService.getPostDetailInfo(postId);
        return ApiResponse.onSuccess(PostConverter.detailPost(post));
    }
    /**
     * post 수정 API
     * @param postId
     * @return ApiResponse
     */
    @PutMapping("/posts/{postId}")
    public ApiResponse<PostDTO.postDetailDTO> changePost(@ExistPost @PathVariable(name = "postId") Long postId,
                                                         @Valid @RequestBody PostDTO.editPostRequestDTO request
                                                            
                                                            ) {
        // System.out.println("userId"+ request.getUserId());
        Post post = postQueryService.updatePost(request, postId); 
        return ApiResponse.onSuccess(PostConverter.detailPost(post));
    
    }


    //stream/private/setvisibility/{userId}/{streamId}  ?visible={bool_visible}
    @PutMapping("/stream-visible/{userId}/{streamId}")
    public ApiResponse<StreamDTO.streamSummaryDTO> changeStreamVisible(@ExistUser @PathVariable(name = "userId") Long userId,
                                                            @ExistStream @PathVariable(name = "streamId") Long streamId,
                                                            @Valid @RequestBody StreamDTO.visibleStreamRequestDTO request
                                                            ){
        Stream stream = streamQueryService.changeVisibleStream(request, userId, streamId);          
        return ApiResponse.onSuccess(StreamConverter.detailStream(stream));
    }
    @PutMapping("/post-visible/{userId}/{postId}")
    public ApiResponse<PostDTO.postDetailDTO> changePostVisible(@ExistUser @PathVariable(name = "userId") Long userId,
                                                            @ExistPost @PathVariable(name = "postId") Long postId,
                                                            @Valid @RequestBody PostDTO.visiblePostRequestDTO request
                                                            ){
                                                       
        Post post = postQueryService.changeVisiblePost(request, userId, postId);          
        return ApiResponse.onSuccess(PostConverter.detailPost(post));
    }

    @PostMapping(value = "/daliyBoard/photo/{diaryId}")
    public  ApiResponse<DiaryDTO.diaryResponseDTO>  saveMultiImage(@ExistDiary @PathVariable(name = "diaryId") Long diaryId,
                                        @Valid @RequestBody DiaryDTO.diaryRequestDTO images) {

                                    
        Diary diary = diaryQueryService.saveDiaryPhotos(diaryId, images.getImages());
       return ApiResponse.onSuccess(DiaryConverter.responsePost(diary));
    }
}
