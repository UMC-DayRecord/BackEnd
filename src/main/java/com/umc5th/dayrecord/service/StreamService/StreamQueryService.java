package com.umc5th.dayrecord.service.StreamService;

import com.umc5th.dayrecord.web.dto.StreamDTO;

public interface StreamQueryService {

    StreamDTO.streamResponseDTO insertStream(StreamDTO.streamCreateDTO request);
}
