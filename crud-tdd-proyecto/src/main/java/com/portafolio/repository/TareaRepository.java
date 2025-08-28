package com.portafolio.repository;

import java.util.List;
import java.util.Optional; // Importar Optional

import com.portafolio.model.Tarea;

public interface TareaRepository {
    Tarea save(Tarea tarea);
    Optional<Tarea> findById(Long id);
    List<Tarea> findAll();
    Optional<Tarea> update(Tarea tarea); // <--- ¡IMPORTANTE! Ahora devuelve Optional<Tarea>
    boolean deleteById(Long id);
}
