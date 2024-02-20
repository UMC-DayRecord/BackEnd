package com.umc5th.dayrecord.web.dto;

import com.umc5th.dayrecord.validation.annotation.ExistComment;
import com.umc5th.dayrecord.validation.annotation.ExistUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class CommentDTO {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class commentRequestDTO {
        @NotEmpty(message = "내용을 입력해 주세요.")
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
        private Boolean isAuthor;
        private String profilePhoto;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class commentListDTO {
        private List<commentResponseDTO> commentList;
        private Integer listSize; // 페이지 크기
        private Boolean hasNext; // 다음 페이지 여부
        private Boolean isFirst; // 첫 번째 페이지인지 여부
        private Boolean isLast; // 마지막 페이지인지 여부
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class commentSizeDTO {
        private Long commentSize;
    }
}
