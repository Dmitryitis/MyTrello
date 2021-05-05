package com.trello.mytrello_api.validation;

import com.trello.mytrello_api.service.UserService;
import com.trello.mytrello_api.validation.annotation.ValidUniqEmail;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqEmailValidator implements ConstraintValidator<ValidUniqEmail, String> {
    @Override
    public void initialize(ValidUniqEmail constraintAnnotation) {

    }

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return userService.findEmail(email) == null;
    }
}
