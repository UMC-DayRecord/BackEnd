package com.umc5th.dayrecord.web.controller;

import com.umc5th.dayrecord.apiPayload.ApiResponse;
import com.umc5th.dayrecord.converter.DiaryPhotoConverter;
import com.umc5th.dayrecord.domain.DiaryPhoto;
import com.umc5th.dayrecord.service.DiaryPhotoService.DiaryPhotoCommandService;
import com.umc5th.dayrecord.web.dto.DiaryPhotoDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Validated
@RequestMapping("/diaryPhoto")
public class DiaryPhotoController {

    private final DiaryPhotoCommandService diaryPhotoCommandService;

    @PutMapping("/change/stream")
    public ApiResponse<DiaryPhotoDTO.photoResponseDTO> changeStream(@RequestBody DiaryPhotoDTO.changePhotoRequestDTO request) {
        DiaryPhoto diaryPhoto = diaryPhotoCommandService.updatePhotoStream(request);
        return ApiResponse.onSuccess(DiaryPhotoConverter.photoInfo(diaryPhoto));
    }

    @PutMapping("/exclude/{diaryPhotoId}")
    public ApiResponse<DiaryPhotoDTO.photoResponseDTO> isExcludePhoto(@PathVariable(name = "diaryPhotoId") Long diaryPhotoId) {
        DiaryPhoto diaryPhoto = diaryPhotoCommandService.changeDelete(diaryPhotoId);
        return ApiResponse.onSuccess(DiaryPhotoConverter.photoInfo(diaryPhoto));
    }
}
