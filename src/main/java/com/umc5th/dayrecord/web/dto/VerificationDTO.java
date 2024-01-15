package com.umc5th.dayrecord.web.dto;

import lombok.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class VerificationDTO {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NickNameRequestDTO {
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
    public static class EmailRequestDTO {
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
    public static class UserRequestDTO {
        @NotEmpty(message = "이메일 주소는 필수 입력 항목입니다.")
        @Size(min = 5, max = 30, message = "이메일 주소는 최소 5자, 최대 30자여야 합니다.")
        @Email(regexp = "^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "유효한 이메일 주소를 입력하여야 합니다.")
        private String email;

        @NotEmpty(message = "닉네임은 필수 입력 항목입니다.")
        @Size(min = 3, max = 20, message = "닉네임은 최소 3자, 최대 30자여야 합니다.")
        private String nickName;

        @NotEmpty(message = "이름은 필수 입력 항목입니다.")
        @Size(min = 3, max = 20, message = "이름은 최소 3자, 최대 30자여야 합니다.")
        private String name;
    }


    /**
     * 이메일 인증 요청(email-verification-request)에 대한 응답 시 사용하는 DTO
     */
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmailCodeVerificationReqResponseDTO {
        private String token;
        private LocalDateTime expireAt;
        // for debugging: 인증번호도 포함해서 보냄
        // TODO: 디버그 기능 삭제
        private String code;
    }


    /**
     * 이메일 인증 토큰에 대한 인증(/verification/verify-email) 요청 시 사용하는 DTO
     */
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmailCodeVerificationRequestDTO {
        @NotEmpty(message = "이메일 인증 요청 토큰은 필수 입력 항목입니다.")
        private String token;

        @NotEmpty(message = "인증번호는 필수 입력 항목입니다.")
        private String code;
    }


    /**
     * 이메일 인증 토큰에 대한 인증(/verification/verify-email) 응답 시 사용하는 DTO
     */
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmailCodeVerificationResponseDTO {
        private String token;
        private Boolean verified;
    }
}
