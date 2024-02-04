package com.umc5th.dayrecord.service.DiaryService;

import com.umc5th.dayrecord.domain.Diary;
import com.umc5th.dayrecord.domain.Stream;

import java.util.List;

public interface DiaryQueryService {

    Diary findDiary(Long streamId);

    Diary saveDiaryPhotos(Long diaryId, List<String> list);

    boolean existById(Long value);
}
