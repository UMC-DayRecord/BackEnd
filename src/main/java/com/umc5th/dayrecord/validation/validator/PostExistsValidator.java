package com.umc5th.dayrecord.validation.validator;

import com.umc5th.dayrecord.apiPayload.code.status.ErrorStatus;
import com.umc5th.dayrecord.service.PostService.PostQueryService;
import com.umc5th.dayrecord.validation.annotation.ExistPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class PostExistsValidator implements ConstraintValidator<ExistPost, Long> {

private final PostQueryService postQueryService;

    @Override
    public void initialize(ExistPost constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (!postQueryService.existById(value)){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus._USER_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
