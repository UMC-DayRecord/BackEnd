package com.umc5th.dayrecord.service.DiaryService;

import com.umc5th.dayrecord.domain.Diary;
import com.umc5th.dayrecord.web.dto.DiaryDTO;

public interface DiaryCommandService {

    Diary recordDetail(DiaryDTO.requestDiaryDetailDTO request);
}
