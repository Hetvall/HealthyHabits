package com.HealthyHabits.HealthyHabits.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidFrecuenciaValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFrecuencia {
    String message() default "La frecuencia debe ser 'Diario', 'Semanal' o 'Mensual'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
} 