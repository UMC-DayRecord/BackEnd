package com.umc5th.dayrecord.service.DiaryService;

import java.util.List;

import com.umc5th.dayrecord.domain.Diary;

public interface DiaryQueryService {
    
    Diary saveDiaryPhotos(Long diaryId, List<String> list);

    boolean existById(Long value);
}
