package com.umc5th.dayrecord.service.CommentService;

import com.umc5th.dayrecord.apiPayload.code.status.ErrorStatus;
import com.umc5th.dayrecord.apiPayload.exception.handler.UserNotFoundHandler;
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
    private final CommentRepository commentRepository;

    /**
     * 댓글 DB 저장
     * @param request
     * @param postId
     * @return Comment
     */
    @Override
    public Comment createComment(CommentDTO.commentRequestDTO request, Long postId, User user) {
        Post post = postRepository.findById(postId).get();
        Comment comment = CommentConverter.saveComment(request, post, user);
        return commentRepository.save(comment);
    }

    /**
     * DB에서 댓글 삭제
     * @param commentId
     * @return Post(삭제 후 해당 게시글 댓글 수 return)
     */
    @Override
    public Integer removeComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId).get();

        // 댓글 작성자와 유저가 다른 경우
        if(!comment.getUser().equals(user))
            throw new UserNotFoundHandler(ErrorStatus._FORBIDDEN);

        Post post = comment.getPost();
        commentRepository.delete(comment);
        return post.getCommentList().size();
    }

    /**
     * 댓글 수정
     * @param request
     * @param commentId
     * @return Comment(수정된 댓글 반환)
     */
    @Override
    public Comment updateComment(CommentDTO.commentRequestDTO request, Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId).get();
        // 댓글 작성자와 유저가 다른 경우
        if(!comment.getUser().equals(user))
            throw new UserNotFoundHandler(ErrorStatus._FORBIDDEN);

        comment.update(request.getDetail());
        return commentRepository.save(comment);
    }
}
