package com.umc5th.dayrecord.service.LikesService;

import com.umc5th.dayrecord.converter.LikesConverter;
import com.umc5th.dayrecord.domain.Likes;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.domain.User;
import com.umc5th.dayrecord.repository.LikesRepository;
import com.umc5th.dayrecord.repository.PostRepository;
import com.umc5th.dayrecord.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikesCommandServiceImpl implements LikesCommandService {

    private final PostRepository postRepository;
    private final LikesRepository likesRepository;


    @Override
    public Boolean updateLikes(Long postId, User user) {
        Post post = postRepository.findById(postId).get();
        Optional<Likes> likes = likesRepository.findByPostAndUser(post, user);
        if(likes.isPresent()) {
            likesRepository.delete(likes.get());
            return likeCheck(post, user);
        } else if(!likes.isPresent()) {
            Likes like = LikesConverter.createLike(post, user);
            likesRepository.save(like);
            return likeCheck(post, user);
        }
        return false;
    }

    @Override
    public Boolean likeCheck(Post post, User user) {
        Optional<Likes> likes = likesRepository.findByPostAndUser(post, user);
        return likes.isPresent();
    }
}
