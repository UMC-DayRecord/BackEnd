package com.umc5th.dayrecord.web.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class PostDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class postSummaryDTO {
        private Long postId;
        private String nickname;
        private List<String> postImg;
        private Long likes; // 좋아요 수
        private Boolean isLike; // 좋아요 클릭 여부
        private Long comments; // 댓글 수
        private Boolean isPublic; // 공개 여부
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class postSummaryListDTO {
        private List<postSummaryDTO> postList;
        private Integer listSize; // 페이지 크기
        private Boolean hasNext; // 다음 페이지 여부
        private Boolean isFirst; // 첫 번째 페이지인지 여부
        private Boolean isLast; // 마지막 페이지인지 여부
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class postDetailDTO {
        private Long postId;
        private String streamName;
        private List<String> postImg;
        private String detail;
        private Long likes; // 좋아요 수
        private Long comments; // 댓글 수
        private Boolean isPublic; // 공개 여부
        private LocalDateTime createdAt;
    }
}
