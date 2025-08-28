I

TRABAJO FINAL TDD


ntegrantes NICOLAS ARANEDA
            NATASSJA JARAMILLO
            MARCELA MORA



# Plan de Testing Ágil para el Sprint: CRUD Tareas

---

## 1. Historias de Usuario para Operaciones CRUD

### Historia 1: Crear Tarea

**Como** usuario
**Quiero** crear una tarea con título y descripción
**Para** almacenar una tarea pendiente y poder gestionarla luego

### Historia 2: Listar Tareas

**Como** usuario
**Quiero** obtener una lista de todas las tareas existentes
**Para** visualizar las tareas pendientes que debo realizar

### Historia 3: Actualizar Tarea

**Como** usuario
**Quiero** modificar el título y la descripción de una tarea existente
**Para** corregir o actualizar la información de una tarea

### Historia 4: Eliminar Tarea

**Como** usuario
**Quiero** eliminar una tarea específica
**Para** marcarla como completada o no necesaria

---

## 2. Criterios de Aceptación Claros por Funcionalidad

| Funcionalidad    | Criterios de Aceptación                                                                                                                                                |
| ---------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Crear Tarea      | - Se debe crear una tarea con título y descripción no vacíos.- El sistema devuelve la tarea creada con su ID asignado.- No se permite crear tareas con campos nulos.   |
| Listar Tareas    | - El sistema devuelve una lista de todas las tareas.- Si no hay tareas, devuelve una lista vacía.                                                                      |
| Actualizar Tarea | - Se actualiza el título y descripción de la tarea si existe.- Devuelve la tarea actualizada.- Si la tarea no existe, devuelve una respuesta vacía o error controlado. |
| Eliminar Tarea   | - El sistema elimina la tarea si existe.- Devuelve `true` si la eliminación fue exitosa, `false` si la tarea no existe.                                                |

---

## 3. Tipos de Pruebas a Realizar

| Tipo de Prueba           | Descripción                                                                                 | Ejemplos en el Sprint                                                                 |
| ------------------------ | ------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- |
| Unitarias                | Pruebas de métodos individuales en aislamiento                                              | `TareaTest` (getters/setters, equals) y `TareaServiceImplTest` (lógica CRUD)          |
| Integración              | Pruebas que verifican la interacción entre componentes (servicio y repositorio con BD real) | `TareaRepositoryImplTest` (persistencia real usando EntityManager)                    |
| Aceptación / Funcionales | Pruebas que validan el cumplimiento de historias desde la perspectiva del usuario           | `TareaControllerTest` (simulación de llamadas a endpoints y validación de respuestas) |

---

## 4. Definición de "Terminado" para el Sprint

Se considera **Terminado** cuando:

* Todas las historias de usuario están desarrolladas e integradas.
* Todas las pruebas unitarias, de integración y de aceptación pasan sin fallos.
* La cobertura de código mínima alcanza al menos 80%.
* El código ha sido revisado (peer review) y está documentado.
* El sistema se puede desplegar y ejecutar sin errores.
* El README incluye instrucciones claras para ejecutar la aplicación y las pruebas.

---

## 5. Plan de Ejecución de Pruebas Durante el Sprint

| Fase del Sprint     | Actividad                                                    | Responsable     | Herramientas            |
| ------------------- | ------------------------------------------------------------ | --------------- | ----------------------- |
| Inicio              | Configurar entorno y dependencias para pruebas               | Equipo Dev      | JUnit 5, Mockito, Maven |
| Desarrollo          | Escribir pruebas unitarias antes de código (TDD)             | Desarrolladores | IDE, JUnit, Mockito     |
| Desarrollo          | Implementar código para pasar las pruebas                    | Desarrolladores | IDE, JUnit              |
| Integración         | Ejecutar pruebas de integración con base de datos en memoria | Equipo QA / Dev | JUnit, EntityManager    |
| Revisión continua   | Code review, pruebas cruzadas, ajustar código/refactorizar   | Equipo Dev      | GitHub, IDE             |
| Pruebas funcionales | Ejecutar pruebas de aceptación a nivel controlador/endpoint  | Equipo QA       | JUnit, Mockito          |
| Finalización        | Generar reportes de cobertura y pruebas                      | Equipo QA / Dev | Jacoco, Maven           |

**Nota:** Los ciclos TDD (Red, Green, Refactor) se integran en el desarrollo de cada historia, asegurando que ninguna funcionalidad se implemente sin su correspondiente prueba previa.

---

## 6. Roles Involucrados en el Sprint

| Rol           | Responsabilidades                                                                                                                     |
| ------------- | ------------------------------------------------------------------------------------------------------------------------------------- |
| Desarrollador | - Escribir código y pruebas (unitarias, integración).- Implementar funcionalidades bajo TDD.- Refactorizar código según feedback.     |
| Tester / QA   | - Ejecutar pruebas funcionales / de aceptación.- Validar criterios de aceptación.- Generar reportes y reportar bugs.                  |
| Líder Técnico | - Revisar código y pruebas (peer review).- Gestionar integración continua.- Asegurar definición de terminado.- Coordinar despliegues. |

---

## 7. Ejemplos de Pruebas en el Sprint

### 7.1. Prueba Unitaria: Crear Tarea (TareaServiceImplTest)

```java
@Test
void testCrearTarea() {
    Tarea tarea = new Tarea("Titulo", "Descripcion");
    Mockito.when(tareaRepository.save(ArgumentMatchers.any(Tarea.class))).thenReturn(tarea);

    Tarea resultado = tareaService.crearTarea("Titulo", "Descripcion");

    Assertions.assertEquals("Titulo", resultado.getTitulo());
    Assertions.assertEquals("Descripcion", resultado.getDescripcion());
}
7.2. Prueba de Integración: Guardar y Obtener Tarea (TareaRepositoryImplTest)

@Test
void testGuardarTarea() {
    Tarea tarea = new Tarea("Tarea 1", "Descripción de la Tarea 1");
    repository.save(tarea);

    List<Tarea> tareas = repository.findAll();
    Assertions.assertEquals(1, tareas.size());
    Assertions.assertEquals("Tarea 1", tareas.get(0).getTitulo());
    Assertions.assertNotNull(tareas.get(0).getId());
}
7.3. Prueba Funcional: Actualizar Tarea (TareaControllerTest)

@Test
void testActualizarTarea() {
    Tarea tarea = new Tarea("Nuevo", "NuevaDesc");
    when(tareaService.actualizarTarea(eq(1L), anyString(), anyString())).thenReturn(Optional.of(tarea));

    Optional<Tarea> resultado = tareaController.actualizarTarea(1L, "Nuevo", "NuevaDesc");

    assertTrue(resultado.isPresent());
    assertEquals("Nuevo", resultado.get().getTitulo());
}
7.4. Prueba Unitaria: Validar Getters y Setters (TareaTest)

@Test
void testSetters() {
    Tarea tarea = new Tarea();
    tarea.setTitulo("Nuevo");
    tarea.setDescripcion("Nueva descripción");

    assertEquals("Nuevo", tarea.getTitulo());
    assertEquals("Nueva descripción", tarea.getDescripcion());
}

# Ciclos de Desarrollo TDD – Proyecto CRUD (23 pruebas)

| Ciclo | Prueba                                              | Resultado Inicial           | Implementación                                    | Refactorización                      |
|-------|----------------------------------------------------|----------------------------|-------------------------------------------------|------------------------------------|
| 1     | testCrearTarea (TareaControllerTest)               | Falla (sin lógica)         | Implementar crearTarea en controlador y servicio | Uso de Mockito para simular servicio |
| 2     | testListarTareas (TareaControllerTest)             | Falla (lista vacía)        | Implementar método listarTareas que retorne lista | Refactorizar mocks y respuesta     |
| 3     | testActualizarTarea (TareaControllerTest)          | Falla (sin actualización) | Implementar actualizar tarea con Optional         | Separar validación y actualización  |
| 4     | testEliminarTarea (TareaControllerTest)             | Falla (no elimina)         | Implementar eliminar tarea retornando booleano   | Mejor manejo del retorno booleano  |
| 5     | testConstructorYGetters (TareaTest)                 | Falla (sin getters)        | Implementar constructor y getters                 | Limpieza de código                 |
| 6     | testSetters (TareaTest)                             | Falla (sin setters)        | Implementar setters                               | Consistencia en setters            |
| 7     | testEqualsYHashCode (TareaTest)                     | Falla (sin equals/hashCode) | Implementar equals y hashCode                      | Mejorar comparaciones              |
| 8     | testGuardarTarea (TareaRepositoryImplTest)          | Falla (sin persistencia)   | Implementar save con transacción                   | Manejo correcto de EntityManager   |
| 9     | testEliminarTarea (TareaRepositoryImplTest)         | Falla (no elimina)         | Implementar deleteById                             | Validar existencia antes de eliminar|
| 10    | testListarTareas (TareaRepositoryImplTest)          | Falla (lista vacía)        | Implementar findAll                               | Mejor manejo de listas             |
| 11    | testActualizarTarea (TareaRepositoryImplTest)       | Falla (sin actualizar)     | Implementar update con Optional                    | Manejo correcto de ID y transacción|
| 12    | testObtenerTareaPorId (TareaRepositoryImplTest)     | Falla (no encuentra)       | Implementar findById                              | Uso correcto de Optional           |
| 13    | testFindByIdNotFound (TareaRepositoryImplTest)      | Falla (sin manejo)         | Retornar Optional.empty si no existe               | Limpieza de código                 |
| 14    | testUpdateNonExistentTarea (TareaRepositoryImplTest) | Falla (actualiza sin comprobar) | Validar existencia antes de actualizar             | Retornar Optional.empty            |
| 15    | testDeleteNonExistentTarea (TareaRepositoryImplTest) | Falla (elimina sin comprobar) | Validar existencia antes de eliminar               | Retornar false cuando no existe   |
| 16    | testFindAllEmpty (TareaRepositoryImplTest)          | Falla (lista no vacía)     | Retornar lista vacía cuando no hay tareas          | Código simplificado                |
| 17    | testSaveNullTarea (TareaRepositoryImplTest)          | Falla (acepta null)        | Validar y lanzar excepción si se guarda null       | Validación en capa repositorio     |
| 18    | testUpdateTareaWithNullFieldsThrowsException (TareaRepositoryImplTest) | Falla (permite campos nulos) | Validar campos no nulos y lanzar excepción          | Manejo de excepciones JPA          |
| 19    | testCrearTarea (TareaServiceImplTest)                | Falla (sin lógica)         | Implementar crearTarea en servicio                  | Uso de mocks para repositorio      |
| 20    | testObtenerTodasLasTareas (TareaServiceImplTest)    | Falla (lista vacía)        | Implementar obtenerTodasLasTareas                   | Refactorizar mocks                 |
| 21    | testActualizarTarea (TareaServiceImplTest)           | Falla (sin actualizar)     | Implementar actualización con Optional             | Separar validación y actualización |
| 22    | testEliminarTarea (TareaServiceImplTest)             | Falla (no elimina)         | Implementar eliminar tarea retornando booleano     | Mejor manejo de booleanos          |
| 23    | testObtenerTareaPorId (TareaServiceImplTest)         | Falla (no encuentra)       | Implementar obtener tarea por ID                     | Uso correcto de Optional           |

---

## Notas generales

- Se usó **Mockito** para simular las dependencias y controlar los retornos.
- Se manejó con cuidado la persistencia y transacciones en los tests del repositorio.
- Se añadió validación para evitar datos inválidos (null o campos vacíos).
- Las pruebas garantizan la funcionalidad de las operaciones CRUD: Crear, Leer, Actualizar y Eliminar.
- La refactorización consistió en mejorar la legibilidad, separar responsabilidades y asegurar consistencia en el manejo de excepciones y Optional.

---
# Proyecto: Sistema CRUD de Tareas con Testing Ágil (TDD)

## 8 Integración con Principios SOLID y Refactorización

Durante el desarrollo de este proyecto de gestión de tareas aplicamos activamente los principios SOLID para garantizar un código mantenible, extensible y de alta calidad. A continuación se detalla cómo se integraron estos principios y qué refactorizaciones fueron necesarias para alinear el diseño del sistema con ellos.

### Principio de Responsabilidad Única (SRP)

Cada clase cumple con una única responsabilidad. Por ejemplo:

- `Tarea`: representa únicamente los datos de una tarea (modelo).
- `TareaRepositoryImpl`: se encarga exclusivamente del acceso a datos.
- `TareaServiceImpl`: maneja la lógica del negocio.
- `TareaController`: actúa como intermediario entre el servicio y la capa de presentación.
- `MainApp`: gestiona la ejecución de la aplicación.

Este diseño facilita la comprensión, mantenimiento y testeo de cada componente de forma aislada.

### Principio de Abierto/Cerrado (OCP)

Las interfaces (`TareaRepository`, `TareaService`) permiten que las clases concretas puedan ser reemplazadas sin modificar el código existente. Por ejemplo, si se quisiera migrar de JPA a JDBC puro, se podría implementar una nueva clase que respete la misma interfaz sin alterar el resto del sistema.

### Principio de Sustitución de Liskov (LSP)

Las implementaciones de `TareaService` y `TareaRepository` pueden ser utilizadas en cualquier parte del sistema donde se esperen sus respectivas interfaces, sin alterar el comportamiento. Las pruebas unitarias verifican que las clases concretas respetan el contrato de sus interfaces.

### Principio de Segregación de Interfaces (ISP)

Se evitó que las interfaces tuvieran métodos innecesarios. Cada interfaz declara únicamente los métodos esenciales que debe cumplir la implementación correspondiente (`crearTarea`, `buscarPorId`, `listarTodas`, etc.).

### Principio de Inversión de Dependencias (DIP)

Se aplica mediante el uso de inyección de dependencias, especialmente en `TareaController` y `TareaServiceImpl`, que dependen de abstracciones y no de implementaciones concretas. Esto facilita el uso de mocks para pruebas unitarias y la flexibilidad del sistema.

### Refactorizaciones Realizadas

Durante el proceso se realizaron diversas refactorizaciones:

- Separación clara entre capas (modelo, repositorio, servicio, controlador, vista).
- Implementación de patrones de diseño como DAO y Service.
- Eliminación de lógica duplicada en métodos de negocio.
- Aplicación de pruebas unitarias que guían las mejoras continuas en el diseño (TDD).
- Mejoras en los nombres de métodos y variables para mayor legibilidad.
- Modularización de lógica compleja (por ejemplo, la validación de datos y el manejo de excepciones).

Estas acciones mejoraron la cohesión del sistema, redujeron el acoplamiento y facilitaron la mantenibilidad del código.

---

## 9. Reflexión Personal (Sección 6)

Desarrollar este proyecto fue una experiencia enriquecedora tanto a nivel técnico como personal. A lo largo del proceso pude aplicar de manera concreta los principios del desarrollo ágil, especialmente el enfoque de **Testing Driven Development (TDD)**, lo cual me permitió mejorar significativamente mi disciplina al escribir pruebas antes que el código de producción.

Una de las lecciones más importantes fue comprender que escribir pruebas no es una tarea adicional, sino parte integral del diseño. Me ayudó a pensar en los requisitos funcionales y a anticipar posibles errores. Esto aumentó mi confianza en el código que escribí, ya que cada funcionalidad estaba respaldada por una prueba automatizada que validaba su comportamiento.

Además, al aplicar los principios **SOLID**, comprendí la importancia de escribir un código limpio, desacoplado y fácilmente extensible. Cada refactorización que realicé no solo mejoró el rendimiento del sistema, sino también su legibilidad y la facilidad para integrar nuevos requerimientos.

Este proyecto también me permitió reforzar buenas prácticas como el uso de patrones de diseño, la escritura de código modular, y la importancia de mantener una arquitectura clara y bien estructurada. Ver cómo cada clase tenía su rol específico y cómo las pruebas confirmaban el correcto funcionamiento del sistema fue altamente gratificante.

En lo personal, este trabajo fortaleció mi confianza como desarrolladora. Me di cuenta de que soy capaz de abordar problemas complejos, dividirlos en tareas más pequeñas y resolverlos paso a paso. El proceso de prueba, error, corrección y mejora constante me demostró que el aprendizaje se logra enfrentando los desafíos y superándolos con perseverancia.

Estoy orgullosa del resultado obtenido y de haber consolidado habilidades clave para mi desarrollo profesional en el área de la programación y el desarrollo de software de calidad.

---





                                                 

```
