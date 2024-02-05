package com.umc5th.dayrecord.service.DiaryService;

import com.umc5th.dayrecord.domain.Diary;
import com.umc5th.dayrecord.domain.Stream;
import com.umc5th.dayrecord.domain.User;
import com.umc5th.dayrecord.web.dto.DiaryDTO;

public interface DiaryCommandService {

    Diary recordDetail(DiaryDTO.requestDiaryDetailDTO request);

    void saveDiary(Long userId);

    void deleteDiary(Diary diary);

    void createDiary(Stream stream, User user);
}
