package com.umc5th.dayrecord.web.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class StreamDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class postSummaryDTO {
        private Long postId;
        private String nickname;
        private List<String> postImg;
        private Long likes;
        private Boolean isLike;
        private Long comments;
        private LocalDateTime createdAt;
    }
}