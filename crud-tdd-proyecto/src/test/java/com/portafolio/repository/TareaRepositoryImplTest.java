package com.portafolio.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.portafolio.model.Tarea;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.RollbackException;

class TareaRepositoryImplTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private TareaRepository repository;

    @BeforeEach
    void setUp() {
        // Cada test tendrá su propia EMF y EM, lo que activa create-drop
        emf = Persistence.createEntityManagerFactory("crud-tdd-unit");
        em = emf.createEntityManager();
        repository = new TareaRepositoryImpl(em); // Inyecta el EM
    }
    
    @AfterEach
    void tearDown() {
        // Asegura que el EntityManager y EntityManagerFactory se cierren.
        // Esto es crucial para que create-drop funcione para cada test.
        if (em != null && em.isOpen()) {
            em.close();
        }
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    @Test
    void testGuardarTarea() {
        Tarea tarea = new Tarea("Tarea 1", "Descripción de la Tarea 1");
        repository.save(tarea); // El repositorio maneja la transacción y el commit

        // Verifica directamente usando el repositorio, que ya accede a la DB
        List<Tarea> tareas = repository.findAll();
        Assertions.assertEquals(1, tareas.size(), "Debería haber 1 tarea después de guardar");
        Assertions.assertEquals("Tarea 1", tareas.get(0).getTitulo());
        Assertions.assertNotNull(tareas.get(0).getId(), "El ID no debería ser nulo");
    }

    @Test
    void testEliminarTarea() {
        Tarea tarea = new Tarea("Tarea 2", "Descripción de la Tarea 2");
        repository.save(tarea); // Guarda la tarea
        Assertions.assertNotNull(tarea.getId(), "La tarea debe tener un ID después de guardarse");

        boolean deleted = repository.deleteById(tarea.getId()); // Elimina la tarea
        Assertions.assertTrue(deleted, "La tarea debería eliminarse correctamente");

        Optional<Tarea> foundTarea = repository.findById(tarea.getId());
        Assertions.assertFalse(foundTarea.isPresent(), "La tarea no debería encontrarse después de eliminar");
    }

    @Test
    void testListarTareas() {
        repository.save(new Tarea("Tarea A", "Descripción A"));
        repository.save(new Tarea("Tarea B", "Descripción B"));

        List<Tarea> tareas = repository.findAll();
        Assertions.assertEquals(2, tareas.size(), "Deberían encontrarse 2 tareas");
    }

    @Test
    void testActualizarTarea() {
        Tarea tarea = new Tarea("Título Original", "Descripción Original");
        repository.save(tarea);
        Assertions.assertNotNull(tarea.getId(), "El ID debe estar poblado para la actualización");

        Tarea updatedTarea = new Tarea("Título Actualizado", "Descripción Actualizada");
        updatedTarea.setId(tarea.getId()); // Usa el ID generado

        Optional<Tarea> result = repository.update(updatedTarea);
        Assertions.assertTrue(result.isPresent(), "La tarea actualizada debe estar presente");
        Assertions.assertEquals("Título Actualizado", result.get().getTitulo());

        // Verifica directamente usando el repositorio
        Optional<Tarea> found = repository.findById(tarea.getId());
        Assertions.assertTrue(found.isPresent(), "La tarea actualizada debe encontrarse en la DB");
        Assertions.assertEquals("Título Actualizado", found.get().getTitulo());
    }

    @Test
    void testObtenerTareaPorId() {
        Tarea tarea = new Tarea("Buscar esta Tarea", "Descripción para buscar");
        repository.save(tarea);
        Assertions.assertNotNull(tarea.getId(), "El ID debe estar poblado para buscar por ID");

        Optional<Tarea> found = repository.findById(tarea.getId());
        Assertions.assertTrue(found.isPresent(), "La tarea debe encontrarse por ID");
        Assertions.assertEquals("Buscar esta Tarea", found.get().getTitulo());
    }

    // --- Nuevos Tests de Integración para aumentar la cobertura ---

    @Test
    void testFindByIdNotFound() {
        // Testea la búsqueda de una tarea con un ID que no existe
        Optional<Tarea> found = repository.findById(99L); // Un ID que sabemos que no existe
        Assertions.assertFalse(found.isPresent(), "No debería encontrarse una tarea con un ID inexistente");
    }

    @Test
    void testUpdateNonExistentTarea() {
        // Testea la actualización de una tarea que no existe
        Tarea nonExistentTarea = new Tarea("No existe", "Esta tarea no debería actualizarse");
        nonExistentTarea.setId(99L); // Un ID que sabemos que no existe

        Optional<Tarea> result = repository.update(nonExistentTarea);
        // Ahora, con la corrección en TareaRepositoryImpl, esto debería ser false
        Assertions.assertFalse(result.isPresent(), "No debería actualizarse una tarea que no existe");
    }

    @Test
    void testDeleteNonExistentTarea() {
        // Testea la eliminación de una tarea con un ID que no existe
        boolean deleted = repository.deleteById(99L); // Un ID que sabemos que no existe
        Assertions.assertFalse(deleted, "No debería eliminarse una tarea que no existe");
    }

    @Test
    void testFindAllEmpty() {
        // Testea que findAll devuelve una lista vacía cuando no hay tareas
        List<Tarea> tareas = repository.findAll();
        Assertions.assertTrue(tareas.isEmpty(), "La lista de tareas debería estar vacía si no se ha guardado ninguna");
    }

    @Test
    void testSaveNullTarea() {
        // Testea el comportamiento al intentar guardar una tarea nula
        Assertions.assertThrows(Exception.class, () -> repository.save(null), "Guardar una tarea nula debería lanzar una excepción");
    }

    @Test
    void testUpdateTareaWithNullFieldsThrowsExceptionForNotNullColumn() {
        // Este test ahora espera que se lance una excepción porque 'titulo' no puede ser nulo.
        Tarea tarea = new Tarea("Título Original", "Descripción Original");
        repository.save(tarea);
        Assertions.assertNotNull(tarea.getId(), "El ID debe estar poblado para la actualización");

        // Crear una tarea con título nulo para intentar actualizar
        Tarea updatedTarea = new Tarea(null, "Descripción Actualizada");
        updatedTarea.setId(tarea.getId());

        // Esperamos que se lance una RollbackException (que envuelve PropertyValueException)
        // porque el título no puede ser nulo en la base de datos.
        Assertions.assertThrows(RollbackException.class, () -> {
            repository.update(updatedTarea);
        }, "Actualizar una tarea con un título nulo debería lanzar una RollbackException");

        // Opcional: Verificar que la tarea original no fue modificada en la DB
        Optional<Tarea> found = repository.findById(tarea.getId());
        Assertions.assertTrue(found.isPresent());
        Assertions.assertEquals("Título Original", found.get().getTitulo());
        Assertions.assertEquals("Descripción Original", found.get().getDescripcion());
    }
}
