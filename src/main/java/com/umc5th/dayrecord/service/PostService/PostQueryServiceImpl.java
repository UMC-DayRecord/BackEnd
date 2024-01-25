package com.umc5th.dayrecord.service.PostService;

import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostQueryServiceImpl implements PostQueryService {

    private final PostRepository postRepository;

    /**
     * 공개 스트림 일기 조회
     * @param userId
     * @param page
     * @return Slice<Post> (사용자가 작성하지 않은 공개 일기 리스트만 반환)
     */
    @Override
    public Slice<Post> getPostList(Long userId, Integer page) {
        Slice<Post> postList = postRepository.findByPost(userId, PageRequest.of(page, 10));
        return postList;
    }

    /**
     * 검색내용이 포함된 일기 조회
     * @param userId
     * @param query
     * @param page
     * @return Slice<Post> (사용자가 작성하지 않은 공개 일기 리스트만 반환)
     * 검색 내용이 포함된 일기 & 검색 내용이 포함된 스트림 키워드에 포함된 일기들
     */
    @Override
    public Post getPost(Long postId) {
        return postRepository.findById(postId).get();
    }

    public Slice<Post> getSearchList(Long userId, String query, Integer page) {
        Slice<Post> postList = postRepository.findBySearchPost(userId, query, PageRequest.of(page, 10));
        return postList;
    }

    /**
     * 일기 상세 조회
     * @param postId
     * @return Post
     */
    public Post getPostDetailInfo(Long postId) {
        Post post = postRepository.findById(postId).get();
        return post;
    }

    /**
     * 일기 유효성 검사
     * @param postId
     * @return Boolean(DB에 해당 post가 존재하지 않으면 => false, 존재하면 => true)
     */
    @Override
    public Boolean existById(Long postId) {
        return postRepository.existsById(postId);
    }
}
