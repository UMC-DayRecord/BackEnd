package com.umc5th.dayrecord.service.DiaryService;

import com.umc5th.dayrecord.apiPayload.code.status.ErrorStatus;
import com.umc5th.dayrecord.apiPayload.exception.handler.DiaryHandler;
import com.umc5th.dayrecord.domain.Diary;
import com.umc5th.dayrecord.domain.Stream;
import com.umc5th.dayrecord.domain.User;
import com.umc5th.dayrecord.repository.DiaryPhotoRepository;
import com.umc5th.dayrecord.repository.DiaryRepository;
import com.umc5th.dayrecord.repository.StreamRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.umc5th.dayrecord.domain.DiaryPhoto;

@Service
@RequiredArgsConstructor
public class DiaryQueryServiceImpl implements DiaryQueryService {

    private final DiaryRepository diaryRepository;
    private final StreamRepository streamRepository;
    private final DiaryPhotoRepository diaryPhotoRepository;

    @Override
    public Diary findDiary(Long streamId) {
        Stream stream = streamRepository.findById(streamId).get();
        return diaryRepository.findByStream(stream);
    }
    /*@Override
    public Diary saveDiaryPhotos(Long diaryId, List<String> images){
        Diary diary = diaryRepository.findById(diaryId).get();
        List<DiaryPhoto> diaryPhotoList = diary.getDiaryPhotoList();
        for (String image : images) {
            DiaryPhoto diaryPhoto = DiaryPhoto.builder().url(image).diary(diary).build();
            diaryPhotoList.add(diaryPhoto);
        }
        diary.setDiaryPhoto(diaryPhotoList);
        diaryRepository.save(diary);
        return diary;
    }*/

    @Override
    public Diary saveDiaryPhotos(User user, List<String> images){
        List<Diary> diaryList = diaryRepository.findAllByUser(user);

        if(diaryList.isEmpty())
            throw new DiaryHandler(ErrorStatus._DIARY_NOT_FOUNT);
        Diary diary = diaryList.get(0);

        for (String image : images) {
            DiaryPhoto diaryPhoto = DiaryPhoto.builder().url(image).diary(diary).build();
            diaryPhotoRepository.save(diaryPhoto);
        }

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

    @Override
    public Slice<Diary> getDaliyBoardDiaryList(Long userId, Integer page){
        Slice<Diary> diaryList = diaryRepository.findByUserId(userId, PageRequest.of(page, 10));
        return diaryList;

    }
    
}
