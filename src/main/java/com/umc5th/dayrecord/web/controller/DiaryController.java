package com.umc5th.dayrecord.web.controller;

import com.umc5th.dayrecord.apiPayload.ApiResponse;
import com.umc5th.dayrecord.apiPayload.code.status.ErrorStatus;
import com.umc5th.dayrecord.apiPayload.exception.handler.UserNotFoundHandler;
import com.umc5th.dayrecord.converter.DiaryConverter;
import com.umc5th.dayrecord.domain.Diary;
import com.umc5th.dayrecord.domain.User;
import com.umc5th.dayrecord.service.DiaryService.DiaryCommandService;
import com.umc5th.dayrecord.service.DiaryService.DiaryQueryService;
import com.umc5th.dayrecord.service.UserQueryService;
import com.umc5th.dayrecord.validation.annotation.ExistStream;
import com.umc5th.dayrecord.validation.annotation.ExistUser;
import com.umc5th.dayrecord.web.dto.DiaryDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Validated
@RequestMapping("/diary")
public class DiaryController {

    private final DiaryQueryService diaryQueryService;
    private final DiaryCommandService diaryCommandService;
    private final UserQueryService userQueryService;

    @GetMapping("/record/{streamId}")
    public ApiResponse<DiaryDTO.diaryDetailDTO> getDiary(@ExistStream @PathVariable(name = "streamId") Long streamId) {
        Diary diary = diaryQueryService.findDiary(streamId);
        return ApiResponse.onSuccess(DiaryConverter.diaryDetailResponse(diary));
    }

    @PutMapping("/record")
    public ApiResponse<DiaryDTO.diaryDetailDTO> recordDiary(@Valid @RequestBody DiaryDTO.requestDiaryDetailDTO request) {
        Diary diary = diaryCommandService.recordDetail(request);
        return ApiResponse.onSuccess(DiaryConverter.diaryDetailResponse(diary));
    }

    /*@PostMapping("/save/{userId}")
    public ApiResponse<String> saveDiary(@ExistUser @PathVariable Long userId) {
        diaryCommandService.saveDiary(userId);

        return ApiResponse.onSuccess("일기를 저장했습니다.");
    }*/

    // jwt 토큰 적용
    /*@PostMapping("/save")
    public ApiResponse<String> saveDiary() {
        // 현재 로그인한 사용자의 사용자 정보 조회
        String loggedInUserNickName = userQueryService.getLoggedInUserNickName()
                .orElseThrow(() -> new UserNotFoundHandler(ErrorStatus._UNAUTHORIZED));

        User user = userQueryService.getUser(loggedInUserNickName)
                .orElseThrow(() -> new UserNotFoundHandler(ErrorStatus._USER_NOT_FOUND));

        diaryCommandService.saveDiary(user);

        return ApiResponse.onSuccess("일기를 저장했습니다.");
    }*/

    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
    @PostMapping("/save")
    public ApiResponse<String> saveDiary() {
        diaryCommandService.saveDiary();

        return ApiResponse.onSuccess("일기를 저장했습니다.");
    }

    @GetMapping("/preview/{streamId}")
    public ApiResponse<DiaryDTO.priviewDiaryResponseDTO> previewDiary(@ExistStream @PathVariable(name = "streamId") Long streamId) {
        Diary diary = diaryQueryService.findDiary(streamId);
        return ApiResponse.onSuccess(DiaryConverter.diaryPreview(diary));
    }
}
