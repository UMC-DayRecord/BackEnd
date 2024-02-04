package com.umc5th.dayrecord.service.DiaryPhotoService;

import com.umc5th.dayrecord.domain.Diary;
import com.umc5th.dayrecord.domain.DiaryPhoto;
import com.umc5th.dayrecord.domain.Stream;
import com.umc5th.dayrecord.repository.DiaryPhotoRepository;
import com.umc5th.dayrecord.repository.StreamRepository;
import com.umc5th.dayrecord.service.DiaryService.DiaryQueryService;
import com.umc5th.dayrecord.web.dto.DiaryPhotoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryPhotoCommandServiceImpl implements DiaryPhotoCommandService {

    private final StreamRepository streamRepository;
    private final DiaryPhotoRepository diaryPhotoRepository;
    private final DiaryQueryService diaryQueryService;

    @Override
    public DiaryPhoto updatePhotoStream(DiaryPhotoDTO.changePhotoRequestDTO request) {

        Stream stream = streamRepository.findById(request.getStreamId()).get();
        DiaryPhoto diaryPhoto = diaryPhotoRepository.findById(request.getDiaryPhotoId()).get();

        Diary diary = diaryQueryService.findDiary(stream);
        diaryPhoto.updateDiary(diary);

        return diaryPhotoRepository.save(diaryPhoto);
    }

    @Override
    public DiaryPhoto changeDelete(Long diaryPhotoId) {
        DiaryPhoto diaryPhoto = diaryPhotoRepository.findById(diaryPhotoId).get();
        diaryPhoto.changeStatus(!diaryPhoto.isStatus());
        return diaryPhotoRepository.save(diaryPhoto);
    }
}
