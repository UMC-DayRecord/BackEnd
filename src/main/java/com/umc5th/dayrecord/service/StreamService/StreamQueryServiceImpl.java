package com.umc5th.dayrecord.service.StreamService;

import com.umc5th.dayrecord.domain.Stream;
import com.umc5th.dayrecord.domain.User;
import com.umc5th.dayrecord.repository.StreamRepository;
import com.umc5th.dayrecord.repository.UserRepository;
import com.umc5th.dayrecord.web.dto.StreamDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamQueryServiceImpl implements StreamQueryService {

    private final StreamRepository streamRepository;
    private final UserRepository userRepository;

    @Override
    public StreamDTO.streamDefaultDTO insertStream(StreamDTO.streamDefaultDTO request) {
        
        // User user = UserConverter.RegisterRequestToUser(request);
        
        Optional<User> user = userRepository.findById(request.getUserId());
        // System.out.println("test" + user.get());
        Stream stream = Stream.builder()
                        .streamName(request.getStreamName())
                        .isPublic(request.getIsPublic())
                        .user(user.get())
                        .build();
        // System.out.println("stream" + stream);
        
        streamRepository.save(stream);

        StreamDTO.streamDefaultDTO streamResponse = StreamDTO.streamDefaultDTO.builder()
            .userId(stream.getUser().getId())
            .isPublic(stream.getIsPublic())
            .streamName(stream.getStreamName())
            .streamId(stream.getId())
            .build();
        return streamResponse;
    }

    @Override
    public List<Stream> getStreamList(Long userId, Integer page){
        List<Stream> streamList = streamRepository.findAll();
        // userId, PageRequest.of(page, 10)
        System.out.println("Stream " + streamList);
        return streamList;
    }
}

