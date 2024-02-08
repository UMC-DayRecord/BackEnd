package com.umc5th.dayrecord.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Slice;

import com.umc5th.dayrecord.domain.Diary;
import com.umc5th.dayrecord.domain.DiaryPhoto;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.web.dto.DiaryDTO;
import com.umc5th.dayrecord.web.dto.PostDTO;

public class DiaryConverter {


    public static DiaryDTO.diaryPhotoResponseDTO fetchPhoto(DiaryPhoto diaryPhoto) {

        return DiaryDTO.diaryPhotoResponseDTO.builder()
                .id(diaryPhoto.getId())
                .url(diaryPhoto.getUrl())
                .status(diaryPhoto.isStatus())
                .build();
    }

    public static DiaryDTO.diaryResponseDTO responsePost(Diary diary) {
        List<DiaryDTO.diaryPhotoResponseDTO> diaryPhotoDTOList
         = diary.getDiaryPhotoList().stream().map((DiaryPhoto diaryPhoto) -> fetchPhoto(diaryPhoto))
         .collect(Collectors.toList());

        return DiaryDTO.diaryResponseDTO.builder()
                .id(diary.getId())
                .isPublic(diary.getIsPublic())
                .detail(diary.getDetail())
                .diaryPhotoList(diaryPhotoDTOList)
                .build();
    }


    public static DiaryDTO.diaryResponseDTO responseDiary(Diary diary) {
        List<DiaryDTO.diaryPhotoResponseDTO> diaryPhotoDTOList
         = diary.getDiaryPhotoList().stream().map((DiaryPhoto diaryPhoto) -> fetchPhoto(diaryPhoto))
         .collect(Collectors.toList());

        return DiaryDTO.diaryResponseDTO.builder()
                .id(diary.getId())
                .isPublic(diary.getIsPublic())
                .detail(diary.getDetail())
                .diaryPhotoList(diaryPhotoDTOList)
                .build();
    }
    

    public static DiaryDTO.diarySummaryListDTO responseSummaryDiary(Slice<Diary> diaryList) {
        List<DiaryDTO.diaryResponseDTO> diaryDTOList = diaryList.stream()
                .map((Diary diary) -> responseDiary(diary))
                .collect(Collectors.toList());
                
        return DiaryDTO.diarySummaryListDTO.builder()
            .diaryList(diaryDTOList)
            .listSize(diaryList.getNumberOfElements())
            .hasNext(diaryList.hasNext())
            .isFirst(diaryList.isFirst())
            .isLast(diaryList.isLast())
            .build();
    }
}
