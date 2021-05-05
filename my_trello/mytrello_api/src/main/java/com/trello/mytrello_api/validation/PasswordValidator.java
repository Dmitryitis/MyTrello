package com.trello.mytrello_api.validation;

import com.trello.mytrello_api.validation.annotation.ValidPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator  implements ConstraintValidator<ValidPassword,String> {
    @Override
    public void initialize(ValidPassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        String pat = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{4,}";
        return password.matches(pat);
    }

}
