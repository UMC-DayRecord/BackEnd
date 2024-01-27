package com.umc5th.dayrecord.validation.validator;

import com.umc5th.dayrecord.apiPayload.code.status.ErrorStatus;
import com.umc5th.dayrecord.validation.annotation.ExistStream;

import com.umc5th.dayrecord.service.StreamService.StreamQueryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class StreamExistsValidator implements ConstraintValidator<ExistStream, Long> {

private final StreamQueryService streamQueryService;

    @Override
    public void initialize(ExistStream constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (!streamQueryService.existById(value)){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus._STREAM_NOT_FOUNT.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
