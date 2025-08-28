# Suite de Automatización Funcional

## Situación Inicial y Objetivo del Proyecto
El área de Calidad de la empresa identificó que los defectos en los flujos de registro e inicio de sesión se descubrían tardíamente, lo que generaba altos costos y retrasos en los proyectos. Para solucionar este problema, se solicitó la creación de una suite de pruebas automatizadas con Selenium.

El objetivo de este proyecto fue desarrollar una suite de pruebas funcional que validara los flujos críticos de registro y login. Además, se buscó generar evidencias de ejecución y establecer una base de pruebas de regresión que pueda ser extendida a futuros módulos de la aplicación.

---

## Arquitectura de Pruebas y Tecnologías
Para asegurar que la suite de pruebas fuera robusta y fácil de mantener, se adoptó el patrón de diseño **Page Object Model (POM)**. Este patrón nos permitió separar la lógica de negocio de los tests de la interacción con los elementos de la interfaz de usuario.

El proyecto se construyó con las siguientes tecnologías:
* **Lenguaje:** Java
* **Automatización:** Selenium WebDriver
* **Framework de Pruebas:** TestNG
* **Gestión de Dependencias:** Maven
* **Gestión de Drivers:** WebDriverManager
* **Entorno de Desarrollo:** Visual Studio Code

### Estructura del Proyecto
El proyecto tiene una estructura clara y organizada:
tu-proyecto-de-automatizacion/
├── src/main/java/
│   └── com/tuempresa/
│       ├── pages/               # Clases "Page Object" con localizadores y métodos de acción.
│       │   └── LoginPage.java
│       └── utils/               # Clases de utilidades, como BaseTest y ScreenshotListener.
├── src/test/java/
│   └── com/tuempresa/
│       └── tests/               # Clases con los escenarios de prueba.
│           └── LoginTest.java
├── pom.xml                      # Archivo de configuración de Maven.
└── testng.xml                   # Archivo de configuración de la suite de TestNG.

---

## Escenarios de Prueba Automatizados
La suite de pruebas cubre los siguientes escenarios funcionales, cumpliendo con las métricas del proyecto:

### Login
* **Login Exitoso:** Se verifica el acceso con credenciales válidas.
* **Login Fallido:** Se valida que el sistema rechace el acceso con credenciales inválidas.
* **Login Fallido con Múltiples Intentos:** Se simulan varios intentos de login fallidos para validar el comportamiento del sistema.

### Registro de Usuario
* **Registro Exitoso:** Se valida que el registro con datos válidos cree un nuevo usuario.
* **Registro Fallido:** Se valida que el formulario de registro muestre mensajes de error con datos inválidos.
* **Pruebas Data-Driven:** Se utiliza **DataProvider** en TestNG para ejecutar 6 combinaciones de datos diferentes en la prueba de registro, aumentando la cobertura de manera eficiente.

---

## Lecciones Aprendidas y Aspectos Clave
Este proyecto fue fundamental para dominar técnicas de automatización funcional. Las principales lecciones aprendidas y prácticas implementadas fueron:

* **Page Object Model (POM):** Implementar el POM desde el inicio permitió crear un código organizado, legible y fácilmente escalable. La separación entre los elementos de la página y la lógica de la prueba es vital para el mantenimiento.
* **Ejecución Cross-Browser:** La configuración en `testng.xml` junto con WebDriverManager nos permitió ejecutar la suite en diferentes navegadores (Chrome y Firefox) sin necesidad de gestionar los drivers manualmente.
* **Generación de Evidencias:** La implementación de un **Listener de TestNG** para capturar capturas de pantalla en caso de fallo es una técnica esencial para la depuración y para la creación de reportes detallados.
* **Sincronización de Elementos:** El uso de **esperas explícitas** con `WebDriverWait` fue crucial para manejar elementos dinámicos y evitar fallos intermitentes en la ejecución de las pruebas.

---

## Instrucciones de Ejecución
Para ejecutar la suite de pruebas, simplemente sigue estos pasos desde la terminal, en la carpeta raíz del proyecto:
```bash
mvn clean test
Entregables y Evidencias
Tras la ejecución, Maven generará automáticamente las evidencias y reportes en la carpeta target/:

Capturas de pantalla: En caso de que una prueba falle, se guardarán automáticamente en la carpeta target/surefire-reports/ScreenshotListener Esto se da en caso de que ub test no pase, si pasan todos los test no ahi captura de pantalla.

Reporte HTML final: Se genera un informe HTML detallado que resume el estado de todas las pruebas en la carpeta target/surefire-reports/index.html.