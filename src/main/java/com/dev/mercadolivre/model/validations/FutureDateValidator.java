package com.dev.mercadolivre.model.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;


public class FutureDateValidator implements ConstraintValidator<FutureDate, LocalDateTime> {

    @Override
    public void initialize(FutureDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        LocalDateTime now = LocalDateTime.now();
        return value.isEqual(now) || value.isBefore(now);
    }
}
