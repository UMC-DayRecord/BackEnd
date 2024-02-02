package com.umc5th.dayrecord.service.DiaryService;

import com.umc5th.dayrecord.domain.Diary;
import com.umc5th.dayrecord.domain.Stream;
import com.umc5th.dayrecord.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryQueryServiceImpl implements DiaryQueryService {

    private final DiaryRepository diaryRepository;

    @Override
    public Diary findDiary(Stream stream) {
        return diaryRepository.findByStream(stream);
    }
}
