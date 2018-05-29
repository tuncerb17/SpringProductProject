package com.tuncerb.constraints;

import com.tuncerb.validators.UserValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by tuncer on 30/05/2018.
 */

@Documented
@Constraint(validatedBy = UserValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameConstraint {
    String message() default "Kullanıcı adı zaten kullanılmakta";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}