



#  Instrucciones de ejecución y configuración




Este proyecto implementa una aplicación CRUD para gestionar tareas, aplicando Testing Ágil (TDD), principios SOLID y buenas prácticas de desarrollo con Java, Maven, JUnit 5 y Mockito. Se utiliza una base de datos H2 en modo archivo para persistencia durante las pruebas.

---

## 🧰 Requisitos del Entorno

* Java 17+
* Maven 3.8+
* JDK configurado en `JAVA_HOME`

---

## 📦 Estructura del Repositorio

```bash
/mi-proyecto-crud-tdd
├── src
│   ├── main
│   │   └── java/com/portafolio/...        # Código fuente
│   └── test
│       └── java/com/portafolio/...        # Clases de prueba JUnit/Mockito
├── target/site/jacoco/                   # Reporte de cobertura (JaCoCo)
├── pom.xml                               # Configuración de dependencias y plugins
├── README.md                             # Este archivo
└── sql-scripts/init.sql (opcional)
```

---

## ▶️ Instrucciones de Ejecución

### 1. Compilar el proyecto

```bash
mvn clean install


```

### 2. Ejecutar pruebas automatizadas con cobertura

```bash
mvn clean test jacoco:report
```

### 3. Ver reporte JaCoCo en navegador

Abrir el archivo:

```
start "" "target/site/jacoco/index.html"
```

### 4.## ▶️ Cómo Ejecutar y Manipular la Aplicación


Tiene una clase principal `com.portafolio.MainApp`) con un método `main`, usa:

```bash
mvn exec:java -Dexec.mainClass="com.portafolio.app.Main"

2. Uso básico
Al iniciar, el programa mostrará un menú con opciones CRUD:

Crear Tarea: Ingresa título y descripción para agregar una nueva tarea.

Listar Tareas: Muestra todas las tareas pendientes con su ID, título y descripción.

Actualizar Tarea: Indica el ID de la tarea a modificar y proporciona el nuevo título y descripción.

Eliminar Tarea: Indica el ID de la tarea que quieres eliminar.

Salir: Termina la aplicación.

3. Ejemplo de flujo de uso
Selecciona "Crear Tarea".

Escribe un título y una descripción.

Selecciona "Listar Tareas" para ver la tarea creada.

Elige "Actualizar Tarea" e ingresa el ID y nuevos datos.

Usa "Eliminar Tarea" para borrar una tarea específica.

Repite o elige "Salir" para finalizar.

4. Persistencia de datos
La aplicación usa la base de datos H2 en modo archivo (jdbc:h2:file:./testdb).

Los datos se mantienen entre ejecuciones mientras no se borre la carpeta o archivo testdb.

Puedes verificar los datos en cualquier momento abriendo la H2 Console siguiendo las siguientes instrucciones 
---

## 🧪 Base de Datos H2 (modo archivo para pruebas)

### Configuración relevante en `application.properties` o similar:
Elimina manualmente el archivo testdb.mv.db (y testdb.trace.db si existe) de la carpeta de tu proyecto. Esto asegura un inicio limpio. Antes de ingresar a la consola de la base de datos


### Ejecutar H2 Console

1. Ejecuta el siguiente comando:

mvn clean test

```bash
java -jar "C:\Users\mamor\.m2\repository\com\h2database\h2\2.2.224\h2-2.2.224.jar" -web
```

2. Abre tu navegador en:

```
http://localhost:8082
```
 la consola debe quedar asi
spring.datasource.url=jdbc:h2:file:./testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create
spring.datasource.hikari.connectionTestQuery=SELECT 1
Apretar el voton Iniciar
```
SELECT * FROM tarea;


 Para cerrar la base de datos desde el terminal en el terminal apretar ejecutar Ctrl + c, asi sale de la Base de datos y vuelve a C:\Users\mamor\OneDrive\Escritorio\crud-tdd-proyecto>

## ✅ Testing Ágil y Cobertura
* Clases de prueba con JUnit5 y Mockito en `/src/test`
* Patrón AAA (Arrange-Act-Assert)
* Cobertura JaCoCo generada en start "" "target/site/jacoco/index.html"
```
---

#