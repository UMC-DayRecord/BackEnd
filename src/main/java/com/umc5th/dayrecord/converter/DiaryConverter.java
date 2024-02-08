package com.umc5th.dayrecord.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.umc5th.dayrecord.domain.Diary;
import com.umc5th.dayrecord.domain.DiaryPhoto;
import com.umc5th.dayrecord.domain.Stream;
import com.umc5th.dayrecord.domain.User;
import com.umc5th.dayrecord.web.dto.DiaryDTO;

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

    public static DiaryDTO.diaryDetailDTO diaryDetailResponse(Diary diary) {
        return DiaryDTO.diaryDetailDTO.builder()
                .streamId(diary.getStream().getId())
                .streamImg(diary.getStream().getStreamPhotoList().get(0).getUrl())
                .detail(diary.getDetail())
                .build();
    }

    public static Diary createDiary(Stream stream, User user) {
        return Diary.builder()
                .stream(stream)
                .user(user)
                .isPublic(stream.getIsPublic())
                .build();
    }

    public static DiaryDTO.priviewDiaryResponseDTO diaryPreview(Diary diary) {
        List<String> diaryPhotoList = diary.getDiaryPhotoList().stream()
                .filter((DiaryPhoto photo) -> !photo.isStatus())
                .map(DiaryPhoto::getUrl)
                .collect(Collectors.toList());

        return DiaryDTO.priviewDiaryResponseDTO.builder()
                .streamId(diary.getStream().getId())
                .steramName(diary.getStream().getStreamName())
                .imageList(diaryPhotoList)
                .imgSize(diaryPhotoList.size())
                .detail(diary.getDetail())
                .createdAt(diary.getCreatedAt())
                .build();
    }
}
