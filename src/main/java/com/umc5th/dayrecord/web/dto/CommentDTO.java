package com.umc5th.dayrecord.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CommentDTO {

    @Getter
    public static class commentRequestDTO {
        private Long userId;
        private String detail;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class commentResponseDTO {
        private String nickname;
        private String detail;
    }
}
