package com.umc5th.dayrecord.service.CommentService;

import com.umc5th.dayrecord.converter.CommentConverter;
import com.umc5th.dayrecord.domain.Comment;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.domain.User;
import com.umc5th.dayrecord.repository.CommentRepository;
import com.umc5th.dayrecord.repository.PostRepository;
import com.umc5th.dayrecord.repository.UserRepository;
import com.umc5th.dayrecord.web.dto.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentCommandServiceImpl implements CommentCommandService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    /**
     * 댓글 DB 저장
     * @param request
     * @param postId
     * @return Comment
     */
    @Override
    public Comment createComment(CommentDTO.commentRequestDTO request, Long postId) {
        Post post = postRepository.findById(postId).get();
        User user = userRepository.findById(request.getUserId()).get();
        Comment comment = CommentConverter.saveComment(request, post, user);
        return commentRepository.save(comment);
    }

    /**
     * DB에서 댓글 삭제
     * @param commentId
     * @return Post(삭제 후 해당 게시글 댓글 수 return)
     */
    @Override
    public Post removeComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).get();
        Post post = comment.getPost();
        commentRepository.delete(comment);
        return post;
    }

    /**
     * 댓글 수정
     * @param request
     * @param commentId
     * @return Comment(수정된 댓글 반환)
     */
    @Override
    public Comment updateComment(CommentDTO.editCommentRequestDTO request, Long commentId) {
        Comment comment = commentRepository.findById(commentId).get();
        comment.update(request.getEditDetail());
        return commentRepository.save(comment);
    }
}
