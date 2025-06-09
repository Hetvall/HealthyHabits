package com.HealthyHabits.HealthyHabits.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "habits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Habit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String tipo; // ejemplo: "Salud Física", "Mental", "Alimentación"
    
    @Column(nullable = false)
    private String frecuencia; // ejemplo: "Diario", "Semanal"
    
    @Column(nullable = false, length = 500)
    private String objetivo; // descripción del propósito
} 