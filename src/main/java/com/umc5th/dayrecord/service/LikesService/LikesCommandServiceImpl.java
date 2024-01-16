package com.umc5th.dayrecord.service.LikesService;

import com.umc5th.dayrecord.converter.LikesConverter;
import com.umc5th.dayrecord.domain.Likes;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.domain.User;
import com.umc5th.dayrecord.repository.LikesRepository;
import com.umc5th.dayrecord.repository.PostRepository;
import com.umc5th.dayrecord.repository.UserRepository;
import com.umc5th.dayrecord.service.PostService.PostCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikesCommandServiceImpl implements LikesCommandService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikesRepository likesRepository;


    @Override
    public Boolean updateLikes(Long postId, Long userId) {
        Post post = postRepository.findById(postId).get();
        User user = userRepository.findById(userId).get();
        Optional<Likes> likes = likesRepository.findByPostAndUser(post, user);
        if(likes.isPresent()) {
            likesRepository.delete(likes.get());
            return false;
        } else if(!likes.isPresent()) {
            Likes like = LikesConverter.createLike(post, user);
            likesRepository.save(like);
            return true;
        }
        return false;
    }
}
