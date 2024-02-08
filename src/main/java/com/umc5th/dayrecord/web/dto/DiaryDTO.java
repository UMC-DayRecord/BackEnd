package com.umc5th.dayrecord.web.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.umc5th.dayrecord.web.dto.PostDTO.postSummaryDTO;

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
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class diarySummaryListDTO {
        private List<diaryResponseDTO> diaryList;
        private Integer listSize; // 페이지 크기
        private Boolean hasNext; // 다음 페이지 여부
        private Boolean isFirst; // 첫 번째 페이지인지 여부
        private Boolean isLast; // 마지막 페이지인지 여부
    }

}
