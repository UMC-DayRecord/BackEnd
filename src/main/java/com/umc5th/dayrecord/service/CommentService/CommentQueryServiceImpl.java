package com.umc5th.dayrecord.service.CommentService;

import com.umc5th.dayrecord.domain.Comment;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.repository.CommentRepository;
import com.umc5th.dayrecord.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentQueryServiceImpl implements CommentQueryService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    /**
     * 게시글 댓글 조회
     * @param postId
     * @param page
     * @return Slice<Comment> (페이징된 댓글 리스트)
     */
    @Override
    public Slice<Comment> commentList(Long postId, Integer page) {
        Post post = postRepository.findById(postId).get();
        Slice<Comment> comments = commentRepository.findAllByPost(post, PageRequest.of(page, 10));
        return comments;
    }

    /**
     * 댓글 유효성 검사
     * @param commentId
     * @return Boolean(DB에 해당 comment가 존재하지 않으면 => false, 존재하면 => true)
     */
    @Override
    public Boolean existById(Long commentId) {
        return commentRepository.existsById(commentId);
    }
}
