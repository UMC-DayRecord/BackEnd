package com.umc5th.dayrecord.web.dto;

import lombok.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class VerificationDTO {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NickNameExistsRequestDTO {
        @NotEmpty(message = "닉네임은 필수 입력 항목입니다.")
        @Size(min = 3, max = 20, message = "닉네임은 최소 3자, 최대 30자여야 합니다.")
        private String nickName;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExistsResponseDTO {
        private boolean isExists;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmailExistsRequestDTO {
        @NotEmpty(message = "이메일 주소는 필수 입력 항목입니다.")
        @Size(min = 5, max = 30, message = "이메일 주소는 최소 5자, 최대 30자여야 합니다.")
        @Email(regexp = "^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "유효한 이메일 주소를 입력하여야 합니다.")
        private String email;
    }


    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmailCodeVerificationRequestDTO {
        @NotEmpty(message = "이메일 주소는 필수 입력 항목입니다.")
        @Size(min = 5, max = 30, message = "이메일 주소는 최소 5자, 최대 30자여야 합니다.")
        @Email(regexp = "^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "유효한 이메일 주소를 입력하여야 합니다.")
        private String email;

        @NotEmpty(message = "이메일 인증 요청 토큰은 필수 입력 항목입니다.")
        private String token;
    }

}
