package com.umc5th.dayrecord.service.DiaryPhotoService;

import com.umc5th.dayrecord.domain.DiaryPhoto;
import com.umc5th.dayrecord.domain.Stream;
import com.umc5th.dayrecord.repository.DiaryPhotoRepository;
import com.umc5th.dayrecord.repository.StreamRepository;
import com.umc5th.dayrecord.web.dto.DiaryPhotoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryPhotoCommandServiceImpl implements DiaryPhotoCommandService {

    private final StreamRepository streamRepository;
    private final DiaryPhotoRepository diaryPhotoRepository;

    @Override
    public DiaryPhoto updatePhotoStream(DiaryPhotoDTO.changePhotoRequestDTO request) {

        Stream stream = streamRepository.findByStreamName(request.getStreamName());
        DiaryPhoto diaryPhoto = diaryPhotoRepository.findById(request.getDiaryPhotoId()).get();

        diaryPhoto.updateStream(stream);
        return diaryPhotoRepository.save(diaryPhoto);
    }
}
