package com.trello.mytrello_api.validation.annotation;


import com.trello.mytrello_api.validation.UniqEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqEmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUniqEmail {
    String message() default "Почта не уникальна";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
