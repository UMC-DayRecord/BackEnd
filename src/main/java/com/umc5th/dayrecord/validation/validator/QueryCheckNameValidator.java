package com.umc5th.dayrecord.validation.validator;

import com.umc5th.dayrecord.apiPayload.code.status.ErrorStatus;
import com.umc5th.dayrecord.validation.annotation.CheckName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class QueryCheckNameValidator implements ConstraintValidator<CheckName, String> {


    @Override
    public void initialize(CheckName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value.length() < 1) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus._QUERY_BAD_REQUEST.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
