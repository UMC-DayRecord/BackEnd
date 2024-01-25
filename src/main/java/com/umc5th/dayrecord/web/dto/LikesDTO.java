package com.umc5th.dayrecord.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class LikesDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class likeResponseDTO {
        private Long postId;
        private Long likes;
        private Boolean like_check;
    }
}
