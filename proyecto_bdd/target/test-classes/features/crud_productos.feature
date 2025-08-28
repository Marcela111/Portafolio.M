# language: es
@CRUD @HU-003 @Regression
Característica: Gestión de productos
  Como administrador del sistema
  Quiero gestionar productos (crear, modificar y eliminar)
  Para mantener el catálogo de productos actualizado

  @SmokeTest
  Escenario: Creación de un nuevo producto
    Dado que el administrador ha iniciado sesión
    Cuando el administrador navega a la página de "Gestión de Productos"
    Y hace clic en el botón "Añadir Producto"
    Y completa el formulario de producto con detalles válidos
    Y hace clic en el botón "Guardar"
    Entonces el nuevo producto "Producto A" debería aparecer en la lista
    Y ver un mensaje de confirmación que dice "Producto guardado con éxito"

  @Regression
  Escenario: Modificación de un producto existente
    Dado que el administrador ha iniciado sesión
    Cuando el administrador navega a la página de "Gestión de Productos"
    Y selecciona el producto "Producto A" para modificar
    Y actualiza el campo de precio a "25.00"
    Y hace clic en el botón "Guardar"
    Entonces el producto "Producto A" debería mostrar el precio "25.00" en la lista
    Y ver un mensaje de confirmación que dice "Producto actualizado con éxito"

  @Regression
  Escenario: Eliminación de un producto
    Dado que el administrador ha iniciado sesión
    Cuando el administrador navega a la página de "Gestión de Productos"
    Y selecciona el producto "Producto A" para eliminar
    Y confirma la eliminación en el cuadro de diálogo
    Entonces el producto "Producto A" no debería aparecer en la lista
    Y ver un mensaje de confirmación que dice "Producto eliminado con éxito"
