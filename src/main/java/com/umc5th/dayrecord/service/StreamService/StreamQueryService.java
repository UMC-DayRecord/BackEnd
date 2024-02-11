package com.umc5th.dayrecord.service.StreamService;

import com.umc5th.dayrecord.web.dto.StreamDTO;
import com.umc5th.dayrecord.web.dto.PostDTO;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.domain.Stream;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface StreamQueryService {

    StreamDTO.streamDefaultDTO insertStream(StreamDTO.streamDefaultDTO request);

    List<Stream> getStreamList(Long userId, Integer page);
    /**
     * 마이스트림 메인화면
     * @param userId
     * @param streamId
     * @param page
     * @return Slice<Post>
     */
    Slice<Post> getStreamPostList(Long userId, Long streamId, Integer page);
    /**
     * 일기보드 화면 출력 API (post)
     * getStreamPostList 과 내용 동일
     * @param userId
     * @param streamId
     * @param page
     * @return Slice<Post>
     */
    Slice<Post> getDaliyBoardList(Long userId, Long streamId, Integer page);
    /**
     * 공개 스트림의 getPostDetailInfo 와 동일.
     * 개별로 수정사항이 있을 것을 대비하여 분리함.
     * @param postId
     * @return
     */
    Post getPostDetailInfo(Long postId);

    Slice<Post> getSearchList(Long userId, String query, Integer page);

    void deleteStream(Long streamId);
    void deletePost(Long postId);

    Boolean existById(Long streamId);

    Stream changeVisibleStream(StreamDTO.visibleStreamRequestDTO request, Long userId, Long streamId);
    //Post updatePost(PostDTO.editPostRequestDTO request, Long postId)
    //  }

    Optional<Stream> getStreamFromLoggedOnUserStreamList(Long streamId);
}
