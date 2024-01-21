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
        @ExistUser
        private Long userId;
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
        @NotEmpty(message = "수정할 내용을 입력해 주세요.")
        private String editDetail;
    }

}
