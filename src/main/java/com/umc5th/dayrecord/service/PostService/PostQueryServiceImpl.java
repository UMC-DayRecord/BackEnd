package com.umc5th.dayrecord.service.PostService;

import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.repository.PostRepository;
import com.umc5th.dayrecord.web.dto.PostDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostQueryServiceImpl implements PostQueryService {

    private final PostRepository postRepository;

    @Override
    public Slice<Post> getPostList(Long userId, Integer page) {
        Slice<Post> postList = postRepository.findByPost(userId, PageRequest.of(page, 10));
        return postList;
    }

    @Override
    public Slice<Post> getSearchList(Long userId, String query, Integer page) {
        Slice<Post> postList = postRepository.findBySearchPost(userId, query, PageRequest.of(page, 10));
        return postList;
    }

    public Post getPostDetailInfo(Long postId) {
        Post post = postRepository.findById(postId).get();
        return post;
    }

    @Override
    public Boolean existById(Long postId) {
        return postRepository.existsById(postId);
    }

    @Override
    public void deletePost(Long postId){
        postRepository.deleteById(postId);
    }
    /**
     * 일기 수정
     * @param request
     * @param commentId
     * @return Comment(수정된 댓글 반환)
     */
    @Override
    public Post updatePost(PostDTO.editPostRequestDTO request, Long postId) {
        Post post = postRepository.findById(postId).get();
        post.update(request.getDetail());
        return postRepository.save(post);
    }

    @Override
    public Post changeVisiblePost(PostDTO.visiblePostRequestDTO request, Long userId, Long streamId){
        Post post = postRepository.findById(request.getPostId()).get();
        post.updateVisible(request.getIsPublic());
        return postRepository.save(post);
    }
}
