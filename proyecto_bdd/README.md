Ficha Técnica del Proyecto de Escenarios de Comportamiento (BDD)
📝 Descripción del Proyecto
Este proyecto tiene como objetivo diseñar y automatizar un conjunto de pruebas basadas en la metodología de Desarrollo Conducido por Comportamiento (BDD). La meta principal es mejorar la comunicación entre los equipos de Negocio, QA y Desarrollo, asegurando la calidad de una aplicación web mediante la creación de escenarios de prueba claros y ejecutables.

Se utilizará la sintaxis Gherkin y el framework Cucumber para definir el comportamiento esperado de la aplicación, implementando la lógica de los pasos en un lenguaje de programación específico.

🛠️ Tecnologías y Herramientas
Framework BDD: Cucumber

Lenguaje de Escenarios: Gherkin

Lenguaje de Implementación: (Java / JavaScript / Python)

Automatización de UI (Opcional): Selenium WebDriver

Entorno de Desarrollo: Visual Studio Code, IntelliJ IDEA, Eclipse o similar

📂 Estructura del Proyecto
La estructura del proyecto sigue las mejores prácticas para la organización de escenarios y sus implementaciones:

├── pom.xml
└── src
    └── test
        ├── java
        │   └── com
        │       └── empresa
        │           └── steps
        │               ├── CrudSteps.java
        │               ├── Hooks.java
        │               └── LoginSteps.java
        └── resources
            └── features
                ├── crud_productos.feature
                ├── login.feature
                └── registro.feature

📋 Requerimientos y Funcionalidades Cubiertas
Este proyecto cumple con los siguientes requerimientos del módulo:

1. Estructura de Escenarios (Gherkin)
Creación de archivos .feature con escenarios redactados en Gherkin (Feature, Scenario, Given, When, Then, And, But).

Implementación de Scenario Outline con la sección Examples para probar múltiples combinaciones de datos.

2. Organización y Filtrado de Pruebas
Uso de etiquetas (Tags) como @SmokeTest, @Regression, @Login para categorizar y filtrar la ejecución de escenarios.

3. Gestión del Ciclo de Vida de las Pruebas
Empleo de Hooks (@Before, @After) para gestionar la inicialización (ej. apertura del navegador) y la finalización (ej. cierre del navegador) de las pruebas de forma centralizada.

4. Trazabilidad y Reporte
Cada escenario hace referencia a la historia de usuario o requerimiento funcional que valida.

Generación de un reporte final (HTML/JSON) que muestra los resultados de la ejecución y la trazabilidad de cada escenario.

▶️ Cómo Ejecutar las Pruebas
Asegúrate de tener todas las dependencias instaladas.

Configura la clase TestRunner para apuntar a la ruta de tus archivos .feature y a la carpeta de tus Step Definitions (glue).

Usa el TestRunner para ejecutar las pruebas, opcionalmente aplicando filtros por tags (ej. @SmokeTest).

✅ Entregables del Proyecto
Archivos .feature: Contienen los escenarios BDD.

Step Definitions: El código que implementa la lógica de cada paso.

Reporte de Ejecución: Archivo de salida que demuestra los resultados y la trazabilidad.

Repositorio Git (Opcional): Enlace al código fuente para su revisión.

https://reports.cucumber.io   Para ver el reporte html
y reporte_json.json, para ver el reporte.json, debes abrir target, luego  cucumber-html-reports y finalmente el reporte.json o el reporte_json.json

"# proyecto_bdd.git." 
