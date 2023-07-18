package com.dev.mercadolivre.model.validations;



import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FutureDateValidator.class)
public @interface FutureDate {

    String message() default "Data nao deve estar no futuro.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
