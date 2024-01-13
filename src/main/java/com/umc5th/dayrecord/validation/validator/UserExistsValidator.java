package com.umc5th.dayrecord.validation.validator;

import com.umc5th.dayrecord.service.UserQueryService;
import com.umc5th.dayrecord.validation.annotation.ExistUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.umc5th.dayrecord.apiPayload.code.status.ErrorStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class UserExistsValidator implements ConstraintValidator<ExistUser, Long> {

    private final UserQueryService userQueryService;

    @Override
    public void initialize(ExistUser constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (!userQueryService.existId(value)){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus._USER_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
