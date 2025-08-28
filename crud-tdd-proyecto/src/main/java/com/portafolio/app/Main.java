package com.portafolio.app; // Puedes ajustar el paquete si es necesario

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.portafolio.model.Tarea;
import com.portafolio.repository.TareaRepositoryImpl;
import com.portafolio.service.TareaService;
import com.portafolio.service.TareaServiceImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        // Inicializa JPA (Hibernate)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("crud-tdd-unit");
        EntityManager em = emf.createEntityManager();

        // Inicializa el repositorio y el servicio
        TareaRepositoryImpl tareaRepository = new TareaRepositoryImpl(em);
        TareaService tareaService = new TareaServiceImpl(tareaRepository);

        Scanner scanner = new Scanner(System.in);
        int opcion;

        System.out.println("--- Aplicación CRUD de Tareas ---");

        do {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Crear Tarea");
            System.out.println("2. Listar Todas las Tareas");
            System.out.println("3. Obtener Tarea por ID");
            System.out.println("4. Actualizar Tarea");
            System.out.println("5. Eliminar Tarea");
            System.out.println("0. Salir");
            System.out.print("Ingrese su opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el título de la tarea: ");
                    String tituloCrear = scanner.nextLine();
                    System.out.print("Ingrese la descripción de la tarea: ");
                    String descripcionCrear = scanner.nextLine();
                    Tarea nuevaTarea = tareaService.crearTarea(tituloCrear, descripcionCrear);
                    System.out.println("Tarea creada: ID " + nuevaTarea.getId() + ", Título: " + nuevaTarea.getTitulo());
                    listarTodasLasTareas(tareaService); // Listar tareas después de crear para ver el cambio
                    break;
                case 2:
                    listarTodasLasTareas(tareaService);
                    break;
                case 3:
                    System.out.print("Ingrese el ID de la tarea a buscar: ");
                    Long idBuscar = scanner.nextLong();
                    scanner.nextLine();
                    Optional<Tarea> tareaEncontrada = tareaService.obtenerTareaPorId(idBuscar);
                    if (tareaEncontrada.isPresent()) {
                        Tarea t = tareaEncontrada.get();
                        System.out.println("Tarea encontrada: ID: " + t.getId() + ", Título: " + t.getTitulo() + ", Descripción: " + t.getDescripcion());
                    } else {
                        System.out.println("Tarea con ID " + idBuscar + " no encontrada.");
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el ID de la tarea a actualizar: ");
                    Long idActualizar = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Ingrese el nuevo título (dejar vacío para no cambiar): ");
                    String nuevoTitulo = scanner.nextLine();
                    System.out.print("Ingrese la nueva descripción (dejar vacío para no cambiar): ");
                    String nuevaDescripcion = scanner.nextLine();

                    Optional<Tarea> tareaActualizada = tareaService.actualizarTarea(idActualizar, nuevoTitulo, nuevaDescripcion);
                    if (tareaActualizada.isPresent()) {
                        System.out.println("Tarea actualizada: ID " + tareaActualizada.get().getId() + ", Título: " + tareaActualizada.get().getTitulo());
                    } else {
                        System.out.println("Tarea con ID " + idActualizar + " no encontrada para actualizar.");
                    }
                    listarTodasLasTareas(tareaService); // Listar tareas después de actualizar para ver el cambio
                    break;
                case 5:
                    System.out.print("Ingrese el ID de la tarea a eliminar: ");
                    Long idEliminar = scanner.nextLong();
                    scanner.nextLine();
                    boolean eliminado = tareaService.eliminarTarea(idEliminar);
                    if (eliminado) {
                        System.out.println("Tarea con ID " + idEliminar + " eliminada correctamente.");
                    } else {
                        System.out.println("No se pudo eliminar la tarea con ID " + idEliminar + " (posiblemente no existe).");
                    }
                    listarTodasLasTareas(tareaService); // Listar tareas después de eliminar para ver el cambio
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicación. ¡Adiós!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);

        // Cierra los recursos de JPA
        em.close();
        emf.close();
        scanner.close();
    }

    // Método auxiliar para listar todas las tareas
    private static void listarTodasLasTareas(TareaService tareaService) {
        System.out.println("--- Listado de Tareas Actual ---");
        List<Tarea> tareas = tareaService.obtenerTodasLasTareas();
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas registradas.");
        } else {
            tareas.forEach(t -> System.out.println("ID: " + t.getId() + ", Título: " + t.getTitulo() + ", Descripción: " + t.getDescripcion()));
        }
    }
}
