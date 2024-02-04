package com.umc5th.dayrecord.service.DiaryService;

import com.umc5th.dayrecord.domain.Diary;
import com.umc5th.dayrecord.domain.Stream;
import com.umc5th.dayrecord.repository.DiaryRepository;
import com.umc5th.dayrecord.repository.StreamRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.umc5th.dayrecord.domain.DiaryPhoto;

@Service
@RequiredArgsConstructor
public class DiaryQueryServiceImpl implements DiaryQueryService {

    private final DiaryRepository diaryRepository;
    private final StreamRepository streamRepository;

    @Override
    public Diary findDiary(Long streamId) {
        Stream stream = streamRepository.findById(streamId).get();
        return diaryRepository.findByStream(stream);
    }
    @Override
    public Diary saveDiaryPhotos(Long diaryId, List<String> images){
        Diary diary = diaryRepository.findById(diaryId).get();
        List<DiaryPhoto> diaryPhotoList = new ArrayList< DiaryPhoto> ();
        for (String image : images) {
            DiaryPhoto diaryPhoto = DiaryPhoto.builder().url(image).build();
            diaryPhotoList.add(diaryPhoto);
        }
        diary.setDiaryPhoto(diaryPhotoList);
        diaryRepository.save(diary);

        return diary;
    }

    /**
     * 다이어리 유효성 검사
     * @param postId
     * @return Boolean(DB에 해당 post가 존재하지 않으면 => false, 존재하면 => true)
     */
    @Override
    public boolean existById(Long diaryId) {
        return diaryRepository.existsById(diaryId);
    }
}
