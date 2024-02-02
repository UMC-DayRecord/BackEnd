package com.umc5th.dayrecord.service.DiaryService;

import com.umc5th.dayrecord.domain.Diary;
import com.umc5th.dayrecord.domain.Stream;

public interface DiaryQueryService {

    Diary findDiary(Stream stream);
}
