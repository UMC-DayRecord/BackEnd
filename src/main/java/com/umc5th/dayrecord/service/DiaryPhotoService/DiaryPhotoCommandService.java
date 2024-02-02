package com.umc5th.dayrecord.service.DiaryPhotoService;

import com.umc5th.dayrecord.domain.DiaryPhoto;
import com.umc5th.dayrecord.web.dto.DiaryPhotoDTO;

public interface DiaryPhotoCommandService {

    DiaryPhoto updatePhotoStream(DiaryPhotoDTO.changePhotoRequestDTO request);
}
