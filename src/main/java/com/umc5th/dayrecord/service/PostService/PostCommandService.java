package com.umc5th.dayrecord.service.PostService;

import com.umc5th.dayrecord.domain.Diary;
import com.umc5th.dayrecord.domain.Post;

public interface PostCommandService {

    Post createPost(Diary diary);
}
