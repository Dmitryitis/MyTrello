package com.trello.mytrello_api.validation.annotation;

import com.trello.mytrello_api.validation.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {

    String message() default "Неправильный формат пароля";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
