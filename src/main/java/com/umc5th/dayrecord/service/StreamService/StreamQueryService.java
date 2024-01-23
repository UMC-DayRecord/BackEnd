package com.umc5th.dayrecord.service.StreamService;

import com.umc5th.dayrecord.web.dto.StreamDTO;

import com.umc5th.dayrecord.domain.Stream;
import org.springframework.data.domain.Slice;

public interface StreamQueryService {

    StreamDTO.streamDefaultDTO insertStream(StreamDTO.streamDefaultDTO request);

    Slice<Stream> getStreamList(Long userId, Integer page);

}
