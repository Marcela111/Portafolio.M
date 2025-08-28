package com.portafolio.controller;

import java.util.List;
import java.util.Optional;

import com.portafolio.model.Tarea;
import com.portafolio.service.TareaService;

public class TareaController {

    private final TareaService tareaService;

    // El servicio se inyecta en el constructor
    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    public Tarea crearTarea(String titulo, String descripcion) {
        return tareaService.crearTarea(titulo, descripcion);
    }

    public List<Tarea> listarTareas() {
        return tareaService.obtenerTodasLasTareas();
    }

    public Optional<Tarea> obtenerTareaPorId(Long id) {
        return tareaService.obtenerTareaPorId(id);
    }

    public Optional<Tarea> actualizarTarea(Long id, String titulo, String descripcion) {
        return tareaService.actualizarTarea(id, titulo, descripcion);
    }

    public boolean eliminarTarea(Long id) {
        return tareaService.eliminarTarea(id);
    }
}