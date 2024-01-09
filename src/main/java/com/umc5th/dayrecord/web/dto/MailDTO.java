package com.umc5th.dayrecord.web.dto;

import lombok.*;

import java.time.LocalDateTime;

public class MailDTO {
    @Builder
    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class MailSendRequestDTO {
        private String title;
        private String content;
        private String targetAddress;
    }

    @Builder
    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class MailSendResponseDTO {
        private LocalDateTime sentAt;
    }
}
