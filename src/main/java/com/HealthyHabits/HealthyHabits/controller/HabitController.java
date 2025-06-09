package com.HealthyHabits.HealthyHabits.controller;

import com.HealthyHabits.HealthyHabits.dto.HabitDTO;
import com.HealthyHabits.HealthyHabits.service.HabitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/habits")
@CrossOrigin(origins = "*")
public class HabitController {

    @Autowired
    private HabitService habitService;

    // POST /habits - Crear un nuevo hábito
    @PostMapping
    public ResponseEntity<HabitDTO> createHabit(@Valid @RequestBody HabitDTO habitDTO) {
        HabitDTO createdHabit = habitService.createHabit(habitDTO);
        return new ResponseEntity<>(createdHabit, HttpStatus.CREATED);
    }

    // GET /habits - Obtener todos los hábitos
    @GetMapping
    public ResponseEntity<List<HabitDTO>> getAllHabits() {
        List<HabitDTO> habits = habitService.getAllHabits();
        if (habits.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(habits, HttpStatus.OK);
    }

    // GET /habits/{id} - Obtener un hábito por ID
    @GetMapping("/{id}")
    public ResponseEntity<HabitDTO> getHabitById(@PathVariable("id") Long id) {
        Optional<HabitDTO> habit = habitService.getHabitById(id);
        if (habit.isPresent()) {
            return new ResponseEntity<>(habit.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // PUT /habits/{id} - Actualizar un hábito existente
    @PutMapping("/{id}")
    public ResponseEntity<HabitDTO> updateHabit(@PathVariable("id") Long id, @Valid @RequestBody HabitDTO habitDTO) {
        Optional<HabitDTO> updatedHabit = habitService.updateHabit(id, habitDTO);
        if (updatedHabit.isPresent()) {
            return new ResponseEntity<>(updatedHabit.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE /habits/{id} - Eliminar un hábito
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteHabit(@PathVariable("id") Long id) {
        boolean deleted = habitService.deleteHabit(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoints adicionales para búsquedas específicas

    // GET /habits/tipo/{tipo} - Obtener hábitos por tipo
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<HabitDTO>> getHabitsByTipo(@PathVariable("tipo") String tipo) {
        List<HabitDTO> habits = habitService.getHabitsByTipo(tipo);
        if (habits.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(habits, HttpStatus.OK);
    }

    // GET /habits/frecuencia/{frecuencia} - Obtener hábitos por frecuencia
    @GetMapping("/frecuencia/{frecuencia}")
    public ResponseEntity<List<HabitDTO>> getHabitsByFrecuencia(@PathVariable("frecuencia") String frecuencia) {
        List<HabitDTO> habits = habitService.getHabitsByFrecuencia(frecuencia);
        if (habits.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(habits, HttpStatus.OK);
    }
} 