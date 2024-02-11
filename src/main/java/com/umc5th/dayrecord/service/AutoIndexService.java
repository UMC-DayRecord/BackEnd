package com.umc5th.dayrecord.service;

import com.umc5th.dayrecord.web.dto.AutoIndexDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AutoIndexService {
    List<String> autoIndex(AutoIndexDTO.AutoIndexRequestDTO request);
}
