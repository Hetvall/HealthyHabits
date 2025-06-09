package com.HealthyHabits.HealthyHabits.dto;

import com.HealthyHabits.HealthyHabits.validation.ValidFrecuencia;
import com.HealthyHabits.HealthyHabits.validation.ValidTipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabitDTO {
    
    private Long id;
    
    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    
    @NotNull(message = "El tipo no puede ser nulo")
    @ValidTipo
    private String tipo; // ejemplo: "Salud Física", "Mental", "Alimentación"
    
    @NotNull(message = "La frecuencia no puede ser nula")
    @ValidFrecuencia
    private String frecuencia; // ejemplo: "Diario", "Semanal", "Mensual"
    
    @NotNull(message = "El objetivo no puede ser nulo")
    @NotBlank(message = "El objetivo no puede estar vacío")
    private String objetivo; // descripción del propósito
} 