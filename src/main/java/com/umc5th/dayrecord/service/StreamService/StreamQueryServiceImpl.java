package com.umc5th.dayrecord.service.StreamService;

import com.umc5th.dayrecord.domain.Stream;
import com.umc5th.dayrecord.domain.User;
import com.umc5th.dayrecord.repository.StreamRepository;
import com.umc5th.dayrecord.repository.UserRepository;
import com.umc5th.dayrecord.web.dto.StreamDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamQueryServiceImpl implements StreamQueryService {

    private final StreamRepository streamRepository;
    private final UserRepository userRepository;


    public StreamDTO.streamResponseDTO insertStream(StreamDTO.streamCreateDTO request) {
        
        // User user = UserConverter.RegisterRequestToUser(request);
        
        Optional<User> user = userRepository.findById(request.getUserId());
        System.out.println("test" + user.get());
        Stream stream = Stream.builder()
                        .streamName(request.getStreamName())
                        .isPublic(request.getIsPublic())
                        .user(user.get())
                        .build();
        System.out.println("stream" + stream);
        
        streamRepository.save(stream);

        StreamDTO.streamResponseDTO streamResponse = StreamDTO.streamResponseDTO.builder()
            .userId(stream.getUser().getId())
            .isPublic(stream.getIsPublic())
            .streamName(stream.getStreamName())
            .streamId(stream.getId())
            .build();
        // Stream stream = streamRepository.save(userId, streamName);
        return streamResponse;
    }

}
