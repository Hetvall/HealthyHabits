package com.HealthyHabits.HealthyHabits.repository;

import com.HealthyHabits.HealthyHabits.entity.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {
    
    // Encontrar hábitos por tipo
    List<Habit> findByTipo(String tipo);
    
    // Encontrar hábitos por frecuencia
    List<Habit> findByFrecuencia(String frecuencia);
    
    // Encontrar hábitos por nombre (búsqueda parcial)
    List<Habit> findByNombreContainingIgnoreCase(String nombre);
    
    // Encontrar hábitos por tipo y frecuencia
    List<Habit> findByTipoAndFrecuencia(String tipo, String frecuencia);
    
    // Consulta personalizada para buscar por palabras clave en objetivo
    @Query("SELECT h FROM Habit h WHERE h.objetivo LIKE %:keyword%")
    List<Habit> findByObjetivoContaining(@Param("keyword") String keyword);
} 