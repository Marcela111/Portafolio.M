# Informe Final de Pruebas de Calidad

**Proyecto:** MediPlus API Test Automation
**QA Responsable:** Natassja Jaramillo, Marcela Mora
**Fecha de Ejecución:** 29 de agosto de 2025

---

## 1. Resumen Ejecutivo

Se ha realizado una validación exhaustiva de la API de MediPlus, que incluyó pruebas funcionales, de seguridad y de rendimiento. Aunque el análisis de rendimiento arrojó resultados positivos en cuanto a la escalabilidad, la ejecución de las pruebas funcionales y de seguridad reveló fallos críticos que impiden que la API sea apta para producción. Los hallazgos más serios incluyen una falta total de autenticación y una implementación incorrecta de los métodos HTTP en los *endpoints*.

---

## 2. Análisis de Pruebas Funcionales y de Seguridad

La ejecución de 9 pruebas de validación funcional y de seguridad resultó en **7 fallas**. Estas fallas indican problemas fundamentales en la lógica de la API, no en el código de prueba.

### 2.1. Fallas Críticas de Seguridad 🚨

Las pruebas de autenticación fallaron, exponiendo una vulnerabilidad severa. La API responde con un código `200 OK` (éxito) incluso cuando se envía un token inválido o cuando no se envía ningún token. Esto significa que los datos son accesibles públicamente.

* `testInvalidAuthToken`: Esperado: `401 Unauthorized`. Obtenido: `200 OK`.
* `testNoAuthToken`: Esperado: `401 Unauthorized`. Obtenido: `200 OK`.

### 2.2. Fallas en la Lógica CRUD

Los métodos para crear, actualizar y eliminar recursos no están habilitados. La API responde consistentemente con `405 Method Not Allowed`, lo que sugiere un error de configuración en el servidor.

* `testPostPatient`: Esperado: `201 Created`. Obtenido: `405 Method Not Allowed`.
* `testPutPatient`: Esperado: `200 OK`. Obtenido: `405 Method Not Allowed`.
* `testDeletePatient`: Esperado: `204 No Content`. Obtenido: `405 Method Not Allowed`.
* `testPostPatientWithInvalidData`: Esperado: `400 Bad Request`. Obtenido: `405 Method Not Allowed`.

### 2.3. Fallas en el Manejo de Respuestas

La API no maneja correctamente las solicitudes a recursos inexistentes, lo que puede confundir a los sistemas cliente.

* `testGetNonExistentPatient`: Esperado: `404 Not Found`. Obtenido: `200 OK`.

---

## 3. Análisis de Pruebas de Rendimiento

Se realizaron pruebas de carga con 10, 50 y 100 usuarios concurrentes para evaluar la escalabilidad de la API. Los resultados fueron en gran medida positivos, demostrando un comportamiento robusto bajo carga.

### 3.1. Tiempo de Respuesta

La latencia promedio de ambas solicitudes se mantuvo baja, incluso con un aumento significativo de usuarios.

| Solicitud | Promedio (10 usuarios) | Promedio (50 usuarios) | Promedio (100 usuarios) | p90 (10 usuarios) | p90 (50 usuarios) | p90 (100 usuarios) |
| :--- | :---: | :---: | :---: | :---: | :---: | :---: |
| GET Pacientes | 3828 ms | 315 ms | 308 ms | 5768 ms | 384 ms | 340 ms |
| POST Citas | 187 ms | 173 ms | 152 ms | 250 ms | 286 ms | 216 ms |

**Conclusión:** La API de `POST Citas` es muy eficiente. La latencia del `GET Pacientes` tuvo un pico inicial, pero mejoró drásticamente bajo mayor carga, sugiriendo la eliminación de un cuello de botella.

### 3.2. Rendimiento (Throughput)

Ambas APIs muestran una escalabilidad excelente. El rendimiento aumenta de forma casi lineal a medida que la carga se incrementa, lo que es una señal de una arquitectura robusta.

| Solicitud | Rendimiento (10 usuarios) | Rendimiento (50 usuarios) | Rendimiento (100 usuarios) |
| :--- | :---: | :---: | :---: |
| GET Pacientes | 0,36 tps | 1,68 tps | 3,33 tps |
| POST Citas | 0,38 tps | 1,69 tps | 3,35 tps |

### 3.3. Descripción del Comportamiento Gráfico

El análisis gráfico muestra que la latencia del `GET Pacientes` comenzó extremadamente alta con baja carga, pero se redujo drásticamente y se estabilizó a medida que el número de usuarios aumentaba. Esto demuestra que un cuello de botella inicial fue eliminado con un mayor volumen de solicitudes. Por otro lado, la latencia del `POST Citas` se mantuvo consistentemente baja, lo que indica una gran eficiencia.

En cuanto al rendimiento, el gráfico ilustra una excelente escalabilidad: tanto las solicitudes `GET` como las `POST` manejan un mayor volumen de transacciones casi de forma lineal a medida que el número de usuarios concurrentes crece. Este es un indicador clave de una arquitectura robusta y bien diseñada.

---

## 4. Conclusiones y Recomendaciones Finales

La API de MediPlus, si bien muestra una buena base de rendimiento, **no está lista para ser desplegada**. Se identificaron problemas de funcionalidad y seguridad que son críticos y deben ser resueltos de inmediato.

**Recomendaciones:**

1.  **Prioridad Alta: Solucionar Vulnerabilidades de Seguridad.** El equipo de desarrollo debe implementar un mecanismo de autenticación robusto y asegurarse de que la API devuelva un `401 Unauthorized` cuando las peticiones no estén autenticadas.
2.  **Corregir la Configuración de los Métodos HTTP.** Los métodos `POST`, `PUT` y `DELETE` deben ser habilitados y configurados para que los *endpoints* respondan con los códigos de estado apropiados (`201`, `200`, `204`).
3.  **Investigar el Warning de JMeter.** La tasa de error del 1% en la prueba de `POST Citas` debe ser investigada para asegurar que la API siempre devuelva la respuesta esperada.
4.  **Implementar Monitoreo de Rendimiento.** Para evitar futuros picos de latencia, se recomienda el uso de una herramienta de monitoreo en el entorno de producción para rastrear y diagnosticar problemas de rendimiento en tiempo real.