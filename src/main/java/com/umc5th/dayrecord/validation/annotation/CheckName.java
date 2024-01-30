package com.umc5th.dayrecord.validation.annotation;

import com.umc5th.dayrecord.validation.validator.QueryCheckNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = QueryCheckNameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckName {
    String message() default "1글자 이상 입력해주세요.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
