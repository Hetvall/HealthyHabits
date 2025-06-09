package com.HealthyHabits.HealthyHabits.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidTipoValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTipo {
    String message() default "El tipo debe ser 'Salud Física', 'Mental' o 'Alimentación'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
} 