package com.umc5th.dayrecord.service.StreamService;

import com.umc5th.dayrecord.web.dto.StreamDTO;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.domain.Stream;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface StreamQueryService {

    StreamDTO.streamDefaultDTO insertStream(StreamDTO.streamDefaultDTO request);

    List<Stream> getStreamList(Long userId, Integer page);
    /**
     * 공개 스트림의 getPostDetailInfo 와 동일.
     * 개별로 수정사항이 있을 것을 대비하여 분리함.
     * @param postId
     * @return
     */
    Post getPostDetailInfo(Long postId);

    Slice<Post> getSearchList(Long userId, String query, Integer page);

    void deleteStream(Long streamId);

    Boolean existById(Long streamId);
}
