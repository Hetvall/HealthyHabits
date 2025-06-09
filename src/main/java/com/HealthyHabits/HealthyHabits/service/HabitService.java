package com.HealthyHabits.HealthyHabits.service;

import com.HealthyHabits.HealthyHabits.dto.HabitDTO;
import com.HealthyHabits.HealthyHabits.entity.Habit;
import com.HealthyHabits.HealthyHabits.repository.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HabitService {

    @Autowired
    private HabitRepository habitRepository;

    // Crear un nuevo hábito
    public HabitDTO createHabit(HabitDTO habitDTO) {
        Habit habit = convertToEntity(habitDTO);
        Habit savedHabit = habitRepository.save(habit);
        return convertToDTO(savedHabit);
    }

    // Obtener todos los hábitos
    public List<HabitDTO> getAllHabits() {
        List<Habit> habits = habitRepository.findAll();
        return habits.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener un hábito por ID
    public Optional<HabitDTO> getHabitById(Long id) {
        Optional<Habit> habit = habitRepository.findById(id);
        return habit.map(this::convertToDTO);
    }

    // Actualizar un hábito existente
    public Optional<HabitDTO> updateHabit(Long id, HabitDTO habitDTO) {
        Optional<Habit> existingHabit = habitRepository.findById(id);
        
        if (existingHabit.isPresent()) {
            Habit habit = existingHabit.get();
            habit.setNombre(habitDTO.getNombre());
            habit.setTipo(habitDTO.getTipo());
            habit.setFrecuencia(habitDTO.getFrecuencia());
            habit.setObjetivo(habitDTO.getObjetivo());
            
            Habit updatedHabit = habitRepository.save(habit);
            return Optional.of(convertToDTO(updatedHabit));
        }
        
        return Optional.empty();
    }

    // Eliminar un hábito
    public boolean deleteHabit(Long id) {
        if (habitRepository.existsById(id)) {
            habitRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Métodos de búsqueda adicionales
    public List<HabitDTO> getHabitsByTipo(String tipo) {
        List<Habit> habits = habitRepository.findByTipo(tipo);
        return habits.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<HabitDTO> getHabitsByFrecuencia(String frecuencia) {
        List<Habit> habits = habitRepository.findByFrecuencia(frecuencia);
        return habits.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Métodos de conversión
    private HabitDTO convertToDTO(Habit habit) {
        return new HabitDTO(
                habit.getId(),
                habit.getNombre(),
                habit.getTipo(),
                habit.getFrecuencia(),
                habit.getObjetivo()
        );
    }

    private Habit convertToEntity(HabitDTO habitDTO) {
        Habit habit = new Habit();
        habit.setId(habitDTO.getId());
        habit.setNombre(habitDTO.getNombre());
        habit.setTipo(habitDTO.getTipo());
        habit.setFrecuencia(habitDTO.getFrecuencia());
        habit.setObjetivo(habitDTO.getObjetivo());
        return habit;
    }
} 