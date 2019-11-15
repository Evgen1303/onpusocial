package com.hunghost.onpusocial.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
    String message() default "Not suitable format for Phone";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}