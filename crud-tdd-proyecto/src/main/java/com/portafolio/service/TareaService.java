package com.portafolio.service;

import java.util.List;
import java.util.Optional;

import com.portafolio.model.Tarea;

public interface TareaService {
    Tarea crearTarea(String titulo, String descripcion);
    List<Tarea> obtenerTodasLasTareas();
    Optional<Tarea> obtenerTareaPorId(Long id);
    Optional<Tarea> actualizarTarea(Long id, String titulo, String descripcion);
    boolean eliminarTarea(Long id);
}
