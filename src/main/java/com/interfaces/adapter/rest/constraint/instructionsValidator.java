package com.interfaces.adapter.rest.constraint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Optional.ofNullable;

@Component
public class instructionsValidator implements ConstraintValidator<ValidateInstruction, String> {
    private final Pattern pattern;

    instructionsValidator(@Value("${application.instructions-pattern}") final String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    @Override
    public boolean isValid(@Nullable final String vin, final ConstraintValidatorContext constraintValidatorContext) {
        return ofNullable(vin)
                .map(pattern::matcher)
                .filter(Matcher::matches)
                .isPresent();
    }
}
