package com.umc5th.dayrecord.service.StreamService;
import com.umc5th.dayrecord.domain.Stream;

import com.umc5th.dayrecord.web.dto.StreamDTO;

public interface StreamQueryService {

    Stream insertStream(StreamDTO.streamCreateDTO request);
}
