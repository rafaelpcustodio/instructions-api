package com.interfaces.adapter.rest.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = instructionsValidator.class)
public @interface ValidateInstruction {
    String message() default "Invalid format for Navigation Instructions code. It should be a String of the following concatenated values: N or S or E or W";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
