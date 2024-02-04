package com.umc5th.dayrecord.service.DiaryService;

import com.umc5th.dayrecord.domain.Diary;
import com.umc5th.dayrecord.repository.DiaryRepository;
import com.umc5th.dayrecord.web.dto.DiaryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryCommandServiceImpl implements DiaryCommandService {

    private final DiaryQueryService diaryQueryService;
    private final DiaryRepository diaryRepository;

    @Override
    public Diary recordDetail(DiaryDTO.requestDiaryDetailDTO request) {
        Diary diary = diaryQueryService.findDiary(request.getStreamId());

        diary.update(request.getDetail());
        return diaryRepository.save(diary);
    }
}
