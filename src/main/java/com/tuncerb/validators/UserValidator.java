package com.tuncerb.validators;

import com.tuncerb.constraints.UsernameConstraint;
import com.tuncerb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by tuncer on 30/05/2018.
 */
public class UserValidator implements
        ConstraintValidator<UsernameConstraint, String> {

    @Autowired
    UserService userService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return userService.isUserNameValid(username);
    }

    @Override
    public void initialize(UsernameConstraint constraintAnnotation) {

    }
}
