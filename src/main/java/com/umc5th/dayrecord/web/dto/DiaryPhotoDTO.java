package com.umc5th.dayrecord.web.dto;

import com.umc5th.dayrecord.validation.annotation.ExistDiaryPhoto;
import com.umc5th.dayrecord.validation.annotation.ExistStream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class DiaryPhotoDTO {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class changePhotoRequestDTO {
        @ExistDiaryPhoto
        private Long diaryPhotoId;
        @ExistStream
        private Long streamId;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class photoResponseDTO {
        private Long diaryPhotoId;
        private Boolean status;
        private Long streamId;
        private String streamName;
    }
}
