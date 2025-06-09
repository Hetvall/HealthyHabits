package com.HealthyHabits.HealthyHabits.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class ValidFrecuenciaValidator implements ConstraintValidator<ValidFrecuencia, String> {
    
    private static final List<String> FRECUENCIAS_VALIDAS = Arrays.asList(
        "Diario", 
        "Semanal", 
        "Mensual"
    );

    @Override
    public void initialize(ValidFrecuencia constraintAnnotation) {
        // No es necesario inicializar nada específico
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return FRECUENCIAS_VALIDAS.contains(value);
    }
} 