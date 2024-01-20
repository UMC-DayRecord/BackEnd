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

    @Override
    public CommentDTO.commentResponseDTO createComment(CommentDTO.commentRequestDTO request, Long postId) {
        Post post = postRepository.findById(postId).get();
        User user = userRepository.findById(request.getUserId()).get();
        Comment comment = CommentConverter.saveComment(request, post, user);
        Comment c = commentRepository.save(comment);
        return CommentConverter.responseComment(c);
    }
}
