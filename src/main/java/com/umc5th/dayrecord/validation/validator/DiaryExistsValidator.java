package com.umc5th.dayrecord.validation.validator;

import com.umc5th.dayrecord.apiPayload.code.status.ErrorStatus;
import com.umc5th.dayrecord.service.DiaryService.DiaryQueryService;
import com.umc5th.dayrecord.service.PostService.PostQueryService;
import com.umc5th.dayrecord.validation.annotation.ExistDiary;
import com.umc5th.dayrecord.validation.annotation.ExistPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class DiaryExistsValidator implements ConstraintValidator<ExistDiary, Long> {

private final DiaryQueryService diaryQueryService;

    @Override
    public void initialize(ExistDiary constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (!diaryQueryService.existById(value)){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus._DIRY_NOT_FOUNT.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
