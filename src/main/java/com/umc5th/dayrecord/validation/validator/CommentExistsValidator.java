package com.umc5th.dayrecord.validation.validator;

import com.umc5th.dayrecord.apiPayload.code.status.ErrorStatus;
import com.umc5th.dayrecord.service.CommentService.CommentQueryService;
import com.umc5th.dayrecord.validation.annotation.ExistComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class CommentExistsValidator implements ConstraintValidator<ExistComment, Long> {

private final CommentQueryService commentQueryService;

    @Override
    public void initialize(ExistComment constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (!commentQueryService.existById(value)){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus._COMMENT_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
