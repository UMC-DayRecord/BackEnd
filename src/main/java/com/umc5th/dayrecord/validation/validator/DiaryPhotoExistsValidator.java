package com.umc5th.dayrecord.validation.validator;

import com.umc5th.dayrecord.apiPayload.code.status.ErrorStatus;
import com.umc5th.dayrecord.service.DiaryPhotoService.DiaryPhotoQueryService;
import com.umc5th.dayrecord.validation.annotation.ExistDiaryPhoto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class DiaryPhotoExistsValidator implements ConstraintValidator<ExistDiaryPhoto, Long> {

    private final DiaryPhotoQueryService diaryPhotoQueryService;

    @Override
    public void initialize(ExistDiaryPhoto constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (!diaryPhotoQueryService.existById(value)){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus._PHOTO_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
