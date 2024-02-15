package com.umc5th.dayrecord.service.DiaryService;

import com.umc5th.dayrecord.domain.Diary;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.domain.Stream;

import java.util.List;

import com.umc5th.dayrecord.domain.User;
import org.springframework.data.domain.Slice;

public interface DiaryQueryService {

    Diary findDiary(Long streamId);

    //Diary saveDiaryPhotos(Long diaryId, List<String> list);
    Diary saveDiaryPhotos(User user, List<String> list);

    boolean existById(Long value);


    /**
     * 일기보드 화면 출력 API (diary)
     * getStreamPostList 과 내용 동일
     * @param userId
     * @param page
     * @return Slice<Diary>
     */
    Slice<Diary> getDaliyBoardDiaryList(Long userId, Integer page);
}
