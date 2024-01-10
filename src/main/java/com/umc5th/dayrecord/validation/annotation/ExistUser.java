package com.umc5th.dayrecord.validation.annotation;

import com.umc5th.dayrecord.validation.validator.UserExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserExistsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistUser {
    String message() default "존재하지 않는 사용자입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
