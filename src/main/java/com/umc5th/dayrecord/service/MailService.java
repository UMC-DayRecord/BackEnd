package com.umc5th.dayrecord.service;

import com.umc5th.dayrecord.web.dto.MailDTO;
import org.springframework.stereotype.Service;

@Service
public interface MailService {
    void sendMessage(MailDTO.MailSendRequestDTO request);
}