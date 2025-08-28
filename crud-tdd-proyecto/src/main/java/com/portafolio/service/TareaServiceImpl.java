package com.portafolio.service;

import java.util.List;
import java.util.Optional;

import com.portafolio.model.Tarea;
import com.portafolio.repository.TareaRepository;

public class TareaServiceImpl implements TareaService {

    private final TareaRepository tareaRepository;

    public TareaServiceImpl(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    @Override
    public Tarea crearTarea(String titulo, String descripcion) {
        Tarea tarea = new Tarea(titulo, descripcion);
        return tareaRepository.save(tarea);
    }

    @Override
    public List<Tarea> obtenerTodasLasTareas() {
        return tareaRepository.findAll();
    }

    @Override
    public Optional<Tarea> obtenerTareaPorId(Long id) {
        return tareaRepository.findById(id);
    }

    @Override
    public Optional<Tarea> actualizarTarea(Long id, String titulo, String descripcion) {
        Optional<Tarea> tareaOpt = tareaRepository.findById(id);
        if (tareaOpt.isPresent()) {
            Tarea tarea = tareaOpt.get();
            
            // Solo actualiza si el nuevo título no es nulo y no está vacío
            if (titulo != null && !titulo.trim().isEmpty()) {
                tarea.setTitulo(titulo);
            }
            // Solo actualiza si la nueva descripción no es nula y no está vacía
            if (descripcion != null && !descripcion.trim().isEmpty()) {
                tarea.setDescripcion(descripcion);
            }
            
            return tareaRepository.update(tarea); 
        }
        return Optional.empty();
    }

    @Override
    public boolean eliminarTarea(Long id) {
        return tareaRepository.deleteById(id);
    }
}
