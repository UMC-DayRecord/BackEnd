package com.umc5th.dayrecord.service.StreamService;

import com.umc5th.dayrecord.domain.Stream;
import com.umc5th.dayrecord.repository.StreamRepository;
import com.umc5th.dayrecord.web.dto.StreamDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StreamQueryServiceImpl implements StreamQueryService {

    private final StreamRepository streamRepository;


    public Stream insertStream(StreamDTO.streamCreateDTO request) {
        
        // User user = UserConverter.RegisterRequestToUser(request);
        
        Stream stream = Stream
                        .builder()
                        .streamName(request.getStreamName())
                        .isPublic(request.getIsPublic())
                        .build();
        streamRepository.save(stream);
        // Stream stream = streamRepository.save(userId, streamName);
        return stream;
    }

}
