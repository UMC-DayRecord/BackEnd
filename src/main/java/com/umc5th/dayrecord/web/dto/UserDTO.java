package com.umc5th.dayrecord.web.dto;

import com.umc5th.dayrecord.domain.UserPhoto;
import lombok.*;

import java.time.LocalDateTime;

public class UserDTO {
    @Getter
    @Builder
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserRegisterRequestDTO {
        private String name;

        private String nickName;

        private String password;

        private String phoneNumber;

//        private UserPhoto userPhoto;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserRegisterResponseDTO {
        private Long id;
        private LocalDateTime createdAt;
    }
}
