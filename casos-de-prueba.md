# Casos de Prueba de la API de MediPlus

Este documento detalla los casos de prueba utilizados para la validación funcional, de seguridad y de rendimiento de la API de MediPlus. Cada caso de prueba incluye su objetivo, los pasos para su ejecución y el resultado esperado, sirviendo como la base metodológica de las pruebas automatizadas.

---

### **1. Casos de Prueba Funcionales (CRUD)**

| ID del Caso de Prueba | Nombre de la Prueba | Descripción del Objetivo | Pasos a Seguir | Resultado Esperado |
| :--- | :--- | :--- | :--- | :--- |
| **MP-TC-001** | **GET Pacientes** | Verificar que la API devuelve una lista de pacientes con un código de estado `200 OK`. | 1. Enviar una solicitud `GET` al endpoint `/patients`.<br>2. Incluir un token de autenticación válido. | 1. La respuesta debe tener un código de estado `200`.<br>2. El cuerpo de la respuesta debe ser un JSON válido que contenga una lista de pacientes. |
| **MP-TC-002** | **POST Paciente Nuevo** | Validar que se puede crear un nuevo paciente enviando los datos requeridos. | 1. Enviar una solicitud `POST` al endpoint `/patients` con los datos del nuevo paciente.<br>2. Incluir un token de autenticación válido. | 1. La respuesta debe tener un código de estado `201 Created`.<br>2. La respuesta debe incluir el ID del nuevo paciente creado. |
| **MP-TC-003** | **PUT Actualizar Paciente** | Comprobar que los datos de un paciente existente pueden ser actualizados. | 1. Identificar un paciente existente.<br>2. Enviar una solicitud `PUT` al endpoint `/patients/{id}` con los datos a actualizar.<br>3. Incluir un token de autenticación válido. | 1. La respuesta debe tener un código de estado `200 OK`.<br>2. El cuerpo de la respuesta debe reflejar los datos actualizados. |
| **MP-TC-004** | **DELETE Paciente** | Confirmar que un paciente puede ser eliminado exitosamente. | 1. Identificar un paciente existente.<br>2. Enviar una solicitud `DELETE` al endpoint `/patients/{id}`.<br>3. Incluir un token de autenticación válido. | La respuesta debe tener un código de estado `204 No Content`. |

---

### **2. Casos de Prueba Negativos y de Seguridad**

| ID del Caso de Prueba | Nombre de la Prueba | Descripción del Objetivo | Pasos a Seguir | Resultado Esperado |
| :--- | :--- | :--- | :--- | :--- |
| **MP-TC-005** | **GET Paciente Inexistente** | Validar que la API maneja correctamente las solicitudes a un recurso que no existe. | 1. Enviar una solicitud `GET` al endpoint `/patients/{id}` (ID que no existe).<br>2. Incluir un token de autenticación válido. | La respuesta debe tener un código de estado `404 Not Found`. |
| **MP-TC-006** | **POST Datos Inválidos** | Verificar que la API rechaza una solicitud con datos faltantes o incorrectos. | 1. Enviar una solicitud `POST` al endpoint `/patients` con un JSON incompleto o incorrecto. | La respuesta debe tener un código de estado `400 Bad Request`. |
| **MP-TC-007** | **Acceso sin Token** | Comprobar que la API niega el acceso a recursos protegidos sin token. | 1. Enviar una solicitud `GET` al endpoint `/patients` **sin** un encabezado de autorización. | La respuesta debe tener un código de estado `401 Unauthorized`. |
| **MP-TC-008** | **Acceso con Token Inválido** | Confirmar que la API rechaza el acceso cuando el token de autenticación es incorrecto. | 1. Enviar una solicitud `GET` al endpoint `/patients` con un token incorrecto o caducado. | La respuesta debe tener un código de estado `401 Unauthorized`. |

---

### **3. Casos de Prueba de Rendimiento**

| ID del Caso de Prueba | Nombre de la Prueba | Descripción del Objetivo | Configuración de Carga | Métrica de Éxito |
| :--- | :--- | :--- | :--- | :--- |
| **MP-TC-009** | **Prueba de Carga (GET)** | Evaluar el tiempo de respuesta y el rendimiento de la API bajo un nivel de carga sostenido. | Aumentar la carga gradualmente de **10 a 100 usuarios concurrentes** durante 1 minuto. | El tiempo de respuesta promedio debe ser **inferior a 1500 ms** para el percentil 90 de usuarios, y el rendimiento debe **escalar linealmente** con el aumento de usuarios. |
| **MP-TC-010** | **Prueba de Carga (POST)** | Medir el rendimiento de la creación de recursos concurrentemente. | Aumentar la carga de **10 a 100 usuarios concurrentes** enviando solicitudes `POST` al endpoint `/citas`. | El tiempo de respuesta promedio debe ser **inferior a 1500 ms** para el percentil 90 de usuarios, y la tasa de error debe ser **inferior al 5%**. |