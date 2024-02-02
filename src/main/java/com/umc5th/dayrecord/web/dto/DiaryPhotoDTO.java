package com.umc5th.dayrecord.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class DiaryPhotoDTO {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class changePhotoRequestDTO {
        private Long diaryPhotoId;
        private String streamName;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class photoResponseDTO {
        private Long diaryPhotoId;
        private Boolean status;
        private String streamName;
    }
}
