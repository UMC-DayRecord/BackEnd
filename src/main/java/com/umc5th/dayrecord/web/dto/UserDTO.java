package com.umc5th.dayrecord.web.dto;

import lombok.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

public class UserDTO {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserRegisterRequestDTO {
        @NotEmpty(message = "이름은 필수 입력 항목입니다.")
        @Size(min = 3, max = 20, message = "이름은 최소 3자, 최대 30자여야 합니다.")
        private String name;

        @NotEmpty(message = "닉네임은 필수 입력 항목입니다.")
        @Size(min = 3, max = 20, message = "닉네임은 최소 3자, 최대 30자여야 합니다.")
        private String nickName;

        @NotEmpty(message = "비밀번호는 필수 입력 항목입니다.")
        @Size(min = 8, max = 255, message = "비밀번호는 최소 8자 이상이어야 합니다.")
        private String password;

        @NotEmpty(message = "전화번호는 필수 입력 항목입니다.")
        @Size(min = 8, max = 15, message = "전화번호는 최소 8자, 최대 15자여야 합니다.")
        @Pattern(regexp = "[0-9]+", message = "전화번호는 숫자로만 구성되어야 합니다.")
        private String phoneNumber;

        @NotEmpty(message = "이메일 주소는 필수 입력 항목입니다.")
        @Size(min = 5, max = 30, message = "이메일 주소는 최소 5자, 최대 30자여야 합니다.")
        @Email(regexp = "^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "유효한 이메일 주소를 입력하여야 합니다.")
        private String email;

//        private UserPhoto userPhoto;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserRegisterResponseDTO {
        private Long id;
        private LocalDateTime createdAt;
    }


    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserLoginRequestDTO {
        @NotEmpty(message = "닉네임은 필수 입력 항목입니다.")
        @Size(min = 3, max = 20, message = "닉네임은 최소 3자, 최대 30자여야 합니다.")
        private String nickName;

        @NotEmpty(message = "비밀번호는 필수 입력 항목입니다.")
        @Size(min = 8, max = 255, message = "비밀번호는 최소 8자 이상이어야 합니다.")
        private String password;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserLoginResponseDTO implements Serializable {
        private static final long serialVersionUID = -8091879091924046844L;

        private String nickName;

        private String token;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindMyIdResponseDTO {
        private String nickName;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindMyIdRequestDTO {
        @NotEmpty(message = "이름은 필수 입력 항목입니다.")
        @Size(min = 3, max = 20, message = "이름은 최소 3자, 최대 30자여야 합니다.")
        private String name;

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
    public static class ResetPasswordRequestDTO {
        @NotEmpty(message = "닉네임은 필수 입력 항목입니다.")
        @Size(min = 3, max = 20, message = "닉네임은 최소 3자, 최대 30자여야 합니다.")
        private String nickName;

        @NotEmpty(message = "이름은 필수 입력 항목입니다.")
        @Size(min = 3, max = 20, message = "이름은 최소 3자, 최대 30자여야 합니다.")
        private String name;

        @NotEmpty(message = "이메일 주소는 필수 입력 항목입니다.")
        @Size(min = 5, max = 30, message = "이메일 주소는 최소 5자, 최대 30자여야 합니다.")
        @Email(regexp = "^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "유효한 이메일 주소를 입력하여야 합니다.")
        private String email;

        @NotEmpty(message = "이메일 인증 토큰은 필수 입력 항목입니다.")
        private String emailVerificationToken;

        @NotEmpty(message = "비밀번호는 필수 입력 항목입니다.")
        @Size(min = 8, max = 255, message = "비밀번호는 최소 8자 이상이어야 합니다.")
        private String password;
    }
}
