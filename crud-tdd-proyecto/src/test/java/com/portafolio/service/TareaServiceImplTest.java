package com.portafolio.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.portafolio.model.Tarea;
import com.portafolio.repository.TareaRepository;

class TareaServiceImplTest {

    @Mock
    private TareaRepository tareaRepository;

    @InjectMocks
    private TareaServiceImpl tareaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearTarea() {
        Tarea tarea = new Tarea("Titulo", "Descripcion");
        Mockito.when(tareaRepository.save(ArgumentMatchers.any(Tarea.class))).thenReturn(tarea);

        Tarea resultado = tareaService.crearTarea("Titulo", "Descripcion");

        Assertions.assertEquals("Titulo", resultado.getTitulo());
        Assertions.assertEquals("Descripcion", resultado.getDescripcion());
    }

    @Test
    void testObtenerTodasLasTareas() {
        List<Tarea> tareas = Arrays.asList(
                new Tarea("T1", "Desc1"),
                new Tarea("T2", "Desc2")
        );
        Mockito.when(tareaRepository.findAll()).thenReturn(tareas);

        List<Tarea> resultado = tareaService.obtenerTodasLasTareas();

        Assertions.assertEquals(2, resultado.size());
    }

    @Test
    void testActualizarTarea() {
        Tarea tareaExistente = new Tarea("Old", "OldDesc");
        tareaExistente.setId(1L); // Asegura que tenga un ID para el findById

        Mockito.when(tareaRepository.findById(1L)).thenReturn(Optional.of(tareaExistente));
        
        // ¡IMPORTANTE! thenAnswer debe devolver un Optional.of(Tarea)
        Mockito.when(tareaRepository.update(ArgumentMatchers.any(Tarea.class)))
               .thenAnswer(invocation -> Optional.of((Tarea) invocation.getArgument(0)));

        Optional<Tarea> resultado = tareaService.actualizarTarea(1L, "Nuevo", "NuevaDesc");

        Assertions.assertTrue(resultado.isPresent());
        Assertions.assertEquals("Nuevo", resultado.get().getTitulo());
        Assertions.assertEquals("NuevaDesc", resultado.get().getDescripcion());
    }

    @Test
    void testEliminarTarea() {
        Mockito.when(tareaRepository.deleteById(1L)).thenReturn(true);

        boolean eliminado = tareaService.eliminarTarea(1L);

        Assertions.assertTrue(eliminado);
    }

    @Test
    void testObtenerTareaPorId() {
        Tarea tarea = new Tarea("Titulo", "Descripcion");
        Mockito.when(tareaRepository.findById(1L)).thenReturn(Optional.of(tarea));

        Optional<Tarea> resultado = tareaService.obtenerTareaPorId(1L);

        Assertions.assertTrue(resultado.isPresent());
        Assertions.assertEquals("Titulo", resultado.get().getTitulo());
    }
}
