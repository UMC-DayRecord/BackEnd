package com.umc5th.dayrecord.service.StreamService;

import com.umc5th.dayrecord.web.dto.StreamDTO;
import org.springframework.stereotype.Service;

@Service
public interface StreamCommandService {
    Boolean changeStreamKeyword(StreamDTO.ChangeStreamKeywordRequestDTO request);
}
