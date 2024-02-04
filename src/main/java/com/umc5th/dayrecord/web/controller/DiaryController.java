package com.umc5th.dayrecord.web.controller;

import com.umc5th.dayrecord.apiPayload.ApiResponse;
import com.umc5th.dayrecord.converter.DiaryConverter;
import com.umc5th.dayrecord.domain.Diary;
import com.umc5th.dayrecord.service.DiaryService.DiaryCommandService;
import com.umc5th.dayrecord.service.DiaryService.DiaryQueryService;
import com.umc5th.dayrecord.validation.annotation.ExistStream;
import com.umc5th.dayrecord.web.dto.DiaryDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
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
}
