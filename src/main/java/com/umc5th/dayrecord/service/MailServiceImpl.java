package com.umc5th.dayrecord.service;

import com.umc5th.dayrecord.apiPayload.code.status.ErrorStatus;
import com.umc5th.dayrecord.apiPayload.exception.handler.EmailHandler;
import com.umc5th.dayrecord.web.dto.MailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    String address;

    @Override
    public MailDTO.MailSendResponseDTO sendMessage(MailDTO.MailSendRequestDTO request) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(address);
//        message.setTo(request.getTargetAddress());
//        message.setSubject(request.getTitle());
//        message.setText(request.getContent());
//
//        try {
//            emailSender.send(message);
//        }
//        catch(Exception e) {
//            // 이메일 전송이 실패한 경우
//            throw new EmailHandler(ErrorStatus._VERIFICATION_REQUEST_EMAIL_SEND_FAILED);
//        }
        return MailDTO.MailSendResponseDTO
                .builder()
                .sentAt(LocalDateTime.now())
                .build();
    }
}
