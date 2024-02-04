package com.umc5th.dayrecord.service.DiaryPhotoService;

import com.umc5th.dayrecord.repository.DiaryPhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryPhotoQueryServiceImpl implements DiaryPhotoQueryService {

    private final DiaryPhotoRepository diaryPhotoRepository;

    @Override
    public boolean existById(Long diaryPhotoId) {
        return diaryPhotoRepository.existsById(diaryPhotoId);
    }
}
