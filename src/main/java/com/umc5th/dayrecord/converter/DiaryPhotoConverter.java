package com.umc5th.dayrecord.converter;

import com.umc5th.dayrecord.domain.DiaryPhoto;
import com.umc5th.dayrecord.web.dto.DiaryPhotoDTO;

public class DiaryPhotoConverter {

    public static DiaryPhotoDTO.photoResponseDTO photoInfo(DiaryPhoto diaryPhoto) {
        return DiaryPhotoDTO.photoResponseDTO.builder()
                .diaryPhotoId(diaryPhoto.getId())
                .status(diaryPhoto.isStatus())
                .streamName(diaryPhoto.getDiary().getStream().getStreamName())
                .build();
    }
}
