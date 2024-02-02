package com.umc5th.dayrecord.web.controller;

import com.umc5th.dayrecord.apiPayload.ApiResponse;
import com.umc5th.dayrecord.converter.DiaryPhotoConverter;
import com.umc5th.dayrecord.domain.DiaryPhoto;
import com.umc5th.dayrecord.service.DiaryPhotoService.DiaryPhotoCommandService;
import com.umc5th.dayrecord.web.dto.DiaryPhotoDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
