package com.portafolio.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class TareaTest {

    @Test
    void testConstructorYGetters() {
        Tarea tarea = new Tarea("Estudiar", "Leer capítulo 5");
        assertEquals("Estudiar", tarea.getTitulo());
        assertEquals("Leer capítulo 5", tarea.getDescripcion());
    }

    @Test
    void testSetters() {
        Tarea tarea = new Tarea();
        tarea.setTitulo("Nuevo");
        tarea.setDescripcion("Nueva descripción");

        assertEquals("Nuevo", tarea.getTitulo());
        assertEquals("Nueva descripción", tarea.getDescripcion());
    }

    @Test
    void testEqualsYHashCode() {
        Tarea tarea1 = new Tarea("A", "B");
        Tarea tarea2 = new Tarea("A", "B");

        assertEquals(tarea1, tarea2);
        assertEquals(tarea1.hashCode(), tarea2.hashCode());
    }
}
