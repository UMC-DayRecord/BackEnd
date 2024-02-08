package com.umc5th.dayrecord.web.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.umc5th.dayrecord.validation.annotation.ExistStream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

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

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class diaryDetailDTO {
        private Long streamId;
        private String streamImg;
        private String detail;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class requestDiaryDetailDTO {
        @ExistStream
        private Long streamId;
        @NotEmpty(message = "내용을 입력해 주세요.")
        private String detail;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class priviewDiaryResponseDTO {
        private Long streamId;
        private String steramName;
        private List<String> imageList;
        private Integer imgSize;
        private String detail;
        private LocalDateTime createdAt;
    }
}
