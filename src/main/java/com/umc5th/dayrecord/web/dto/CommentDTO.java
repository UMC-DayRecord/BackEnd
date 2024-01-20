package com.umc5th.dayrecord.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class CommentDTO {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class commentRequestDTO {
        private Long userId;
        private String detail;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class commentResponseDTO {
        private Long commentId;
        private String nickname;
        private String detail;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class commentListDTO {
        private List<commentResponseDTO> commentList;
        private Long listSize;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class editCommentRequestDTO {
        private String editDetail;
    }

}
