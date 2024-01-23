package com.umc5th.dayrecord.web.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class StreamDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class streamResponseDTO {
        private Long userId;
        private Long streamId;
        private String streamName;
        private Boolean isPublic; // 공개 여부
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class streamCreateDTO {
        private Long userId;
        private Long streamId;
        private String streamName;
        private Boolean isPublic; // 공개 여부
    }
}
