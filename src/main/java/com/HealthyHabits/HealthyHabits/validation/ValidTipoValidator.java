package com.HealthyHabits.HealthyHabits.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class ValidTipoValidator implements ConstraintValidator<ValidTipo, String> {
    
    private static final List<String> TIPOS_VALIDOS = Arrays.asList(
        "Salud Física", 
        "Mental", 
        "Alimentación"
    );

    @Override
    public void initialize(ValidTipo constraintAnnotation) {
        // No es necesario inicializar nada específico
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return TIPOS_VALIDOS.contains(value);
    }
} 