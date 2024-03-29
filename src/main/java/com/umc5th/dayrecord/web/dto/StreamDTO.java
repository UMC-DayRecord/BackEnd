package com.umc5th.dayrecord.web.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import com.umc5th.dayrecord.validation.annotation.ExistStream;
import com.umc5th.dayrecord.web.dto.PostDTO.postSummaryDTO;

import javax.validation.constraints.NotNull;

public class StreamDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class streamDefaultDTO {
        private Long userId;
        private Long streamId;
        private String streamName;
        private Boolean isPublic; // 공개 여부
        private String keyword;
        private Integer page;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class streamSummaryDTO {
        private Long userId;
        private Long streamId;
        private String streamName;
        private String keyword;
        //private List<postSummaryDTO> postList;
        private Boolean isPublic; // 공개 여부
    }
 
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class streamSummaryListDTO {
        private List<streamSummaryDTO> streamList;
        private Integer listSize; // 페이지 크기
        private Boolean hasNext; // 다음 페이지 여부
        private Boolean isFirst; // 첫 번째 페이지인지 여부
        private Boolean isLast; // 마지막 페이지인지 여부
    }  

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class visibleStreamRequestDTO {
        private Boolean isPublic;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChangeStreamKeywordRequestDTO {
        @NotNull(message = "변경할 스트림 키워드는 반드시 입력해야 합니다.")
        private String keyword;
        private Long streamId;
    }
}
