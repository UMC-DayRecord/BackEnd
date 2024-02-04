package com.umc5th.dayrecord.web.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class DiaryDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class diaryRequestDTO {
        private List<String> images;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class diaryPhotoResponseDTO {
        private Long id;
        private String url;
        private boolean status;
    }


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class diaryResponseDTO {

        private Long id;

        private String detail;

        private Boolean isPublic;
        private List<diaryPhotoResponseDTO> diaryPhotoList;

    }

}
