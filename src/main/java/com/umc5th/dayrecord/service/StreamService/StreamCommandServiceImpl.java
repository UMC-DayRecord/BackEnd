package com.umc5th.dayrecord.service.StreamService;

import com.umc5th.dayrecord.apiPayload.code.status.ErrorStatus;
import com.umc5th.dayrecord.apiPayload.exception.GeneralException;
import com.umc5th.dayrecord.domain.Stream;
import com.umc5th.dayrecord.repository.StreamRepository;
import com.umc5th.dayrecord.web.dto.StreamDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StreamCommandServiceImpl implements StreamCommandService {
    private final StreamQueryService streamQueryService;
    private final StreamRepository streamRepository;

    /**
     * 스트림 키워드를 변경합니다.
     * @param request StreamDTO.ChangeStreamKeywordRequestDTO
     * @return boolean
     */
    @Override
    public Boolean changeStreamKeyword(StreamDTO.ChangeStreamKeywordRequestDTO request) {
        Stream targetStream = streamQueryService.getStreamFromLoggedOnUserStreamList(request.getStreamId())
                .orElseThrow(() -> new GeneralException(ErrorStatus._STREAM_NOT_FOUNT));

        // 타겟 스트림을 찾은 경우 키워드 변경
        targetStream.setKeyword(request.getKeyword());

        // 키워드 변경한 스트림을 DB에 저장
        streamRepository.save(targetStream);
        return true;
    }
}
