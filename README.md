Pruebas Automatizadas de la API de MediPlus
Este proyecto contiene una suite de pruebas de calidad automatizadas para la API REST de MediPlus, cubriendo la validación funcional, de seguridad y de rendimiento.

Contenido del Repositorio
src/test/java: Código fuente de las pruebas funcionales y de seguridad con REST Assured y TestNG.

scripts/Mediplus-LoadTest.jmx: Script de JMeter para las pruebas de rendimiento.

pom.xml: Archivo de configuración de Maven con las dependencias necesarias.

Requisitos Previos
Para ejecutar las pruebas, asegúrate de tener instalados los siguientes programas:

Java JDK 8+

Maven 3.6+

Apache JMeter 5.6+

¿Cómo ejecutar las pruebas?
Pruebas Funcionales (Java/REST Assured)
Clona este repositorio.

Navega a la carpeta raíz del proyecto en tu terminal.

Ejecuta el siguiente comando para correr todas las pruebas:

Bash

mvn clean test
Pruebas de Rendimiento (JMeter)
Abre la aplicación Apache JMeter.

Ve a File > Open y selecciona el archivo scripts/Mediplus-LoadTest.jmx.

Ejecuta la prueba desde la interfaz de usuario. Los resultados se mostrarán en los listeners configurados.

Resultados de la Ejecución
La ejecución de las pruebas funcionales ha revelado 7 fallos de 9 pruebas totales. Estos fallos no se deben a errores en el código de prueba, sino a vulnerabilidades y comportamientos inesperados de la API.

Hallazgos Críticos:

Problemas de Autenticación: Las pruebas de seguridad fallaron porque la API responde con éxito (200 OK) incluso sin un token de autenticación válido, lo que representa un grave riesgo de seguridad.

Métodos HTTP Deshabilitados: Los métodos POST, PUT y DELETE para crear, actualizar y eliminar recursos no están permitidos en los endpoints correspondientes. La API respondió con un error 405 Method Not Allowed en todos los casos.

El informe ejecutivo completo con el análisis de cada falla y las recomendaciones detalladas para el equipo de desarrollo se encuentra en la documentación adjunta.