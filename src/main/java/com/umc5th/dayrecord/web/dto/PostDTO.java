package com.umc5th.dayrecord.web.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class PostDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class postDTO {
        private Long postId;
        private String nickname;
        private List<String> postImg;
        private Long likes;
        private Long comments;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class postListDTO {
        private List<postDTO> postList;
        private Integer listSize; // 페이지 크기
        private Boolean isFirst; // 첫 번째 페이지인지 여부
        private Boolean isLast; // 마지막 페이지인지 여부
    }

}
