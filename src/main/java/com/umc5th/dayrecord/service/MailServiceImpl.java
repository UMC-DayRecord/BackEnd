package com.umc5th.dayrecord.service;

import com.umc5th.dayrecord.web.dto.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    String address;

    @Override
    public void sendMessage(MailDTO.MailSendRequestDTO request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(address);
        message.setTo(request.getTargetAddress());
        message.setSubject(request.getTitle());
        message.setText(request.getContent());

        emailSender.send(message);
    }
}
