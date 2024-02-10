package com.umc5th.dayrecord.service.PostService;

import com.umc5th.dayrecord.converter.PostConverter;
import com.umc5th.dayrecord.domain.Diary;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.domain.PostPhoto;
import com.umc5th.dayrecord.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostCommandServiceImpl implements PostCommandService {

    private final PostRepository postRepository;

    @Override
    public Post createPost(Diary diary) {
        Post post = PostConverter.createPost(diary);

        // DiaryPhoto를 PostPhoto로 변환하여 List<PostPhoto> 생성
        List<PostPhoto> photoList = diary.getDiaryPhotoList().stream()
                .filter(diaryPhoto -> !diaryPhoto.isStatus())
                .map(diaryPhoto -> PostConverter.convertDiaryPhoto(diaryPhoto, post))
                .collect(Collectors.toList());

        // post와 photoList 연결
        post.getPostPhotoList().addAll(photoList);

        return postRepository.save(post);
    }
}
