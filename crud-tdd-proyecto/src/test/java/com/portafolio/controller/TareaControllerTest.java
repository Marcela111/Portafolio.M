package com.portafolio.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.portafolio.model.Tarea;
import com.portafolio.service.TareaService;

class TareaControllerTest {

    // Se mantiene la dependencia mockeada
    @Mock
    private TareaService tareaService;

    // Se utiliza @InjectMocks para crear el controlador
    @InjectMocks
    private TareaController tareaController;

    @BeforeEach
    void setUp() {
        // Esta línea es CRUCIAL para inicializar los objetos @Mock y @InjectMocks
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testCrearTarea() {
        Tarea tarea = new Tarea("Test", "Desc");
        when(tareaService.crearTarea(anyString(), anyString())).thenReturn(tarea);

        Tarea resultado = tareaController.crearTarea("Test", "Desc");

        assertEquals("Test", resultado.getTitulo());
        assertEquals("Desc", resultado.getDescripcion());
    }

    @Test
    void testListarTareas() {
        List<Tarea> tareas = Arrays.asList(new Tarea("T1", "D1"), new Tarea("T2", "D2"));
        when(tareaService.obtenerTodasLasTareas()).thenReturn(tareas);

        List<Tarea> resultado = tareaController.listarTareas();

        assertEquals(2, resultado.size());
    }

    @Test
    void testActualizarTarea() {
        Tarea tarea = new Tarea("Nuevo", "NuevaDesc");
        when(tareaService.actualizarTarea(eq(1L), anyString(), anyString())).thenReturn(Optional.of(tarea));

        Optional<Tarea> resultado = tareaController.actualizarTarea(1L, "Nuevo", "NuevaDesc");

        assertTrue(resultado.isPresent());
        assertEquals("Nuevo", resultado.get().getTitulo());
    }

    @Test
    void testEliminarTarea() {
        when(tareaService.eliminarTarea(1L)).thenReturn(true);

        boolean resultado = tareaController.eliminarTarea(1L);

        assertTrue(resultado);
    }
}