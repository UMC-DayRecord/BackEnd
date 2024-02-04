package com.umc5th.dayrecord.converter;

import com.umc5th.dayrecord.web.dto.StreamDTO;
import com.umc5th.dayrecord.domain.Stream;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.stream.Collectors;

public class StreamConverter {
    
    public static StreamDTO.streamSummaryDTO fetchStream(Stream stream) {
        // boolean like_check = stream.getLikesList().stream()
        //         .anyMatch(like -> like != null && like.getUser().getId().equals(userId));

        return StreamDTO.streamSummaryDTO.builder()
                .streamId(stream.getId())
                .streamName(stream.getStreamName())
                .isPublic(stream.getIsPublic())
                .userId(stream.getUser().getId())
                .build();
    }

    public static StreamDTO.streamSummaryListDTO responseStream(List<Stream> streamList) {
        List<StreamDTO.streamSummaryDTO> streamDTOList = streamList.stream()
                .map((Stream stream) -> fetchStream(stream))
                .collect(Collectors.toList());

        return StreamDTO.streamSummaryListDTO.builder()
                .streamList(streamDTOList)
                // .listSize(streamList.getNumberOfElements())
                // .hasNext(streamList.hasNext())
                // .isFirst(streamList.isFirst())
                // .isLast(streamList.isLast())
                .build();
    }


    public static StreamDTO.streamSummaryDTO detailStream(Stream stream) {

        return StreamDTO.streamSummaryDTO.builder()
                .streamId(stream.getId())
                .streamName(stream.getStreamName())
                .isPublic(stream.getIsPublic())
                .userId(stream.getUser().getId())
                .build();
    }
}
