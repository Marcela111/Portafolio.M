#language: es

@Login @HU-001 @Regression
Característica: Gestión de autenticación de usuarios en la plataforma.
  Como un usuario de la plataforma
  Quiero poder iniciar sesión y cerrar sesión
  Para acceder a mi panel de control de forma segura

  @SmokeTest
  Escenario: Inicio de sesión exitoso con credenciales válidas
    Dado que el usuario navega a la página de inicio de sesión
    Cuando el usuario ingresa "usuario_valido" en el campo de usuario y "clave_secreta" en el de contraseña
    Y hace clic en el botón "Ingresar"
    Entonces el usuario debería ser redirigido al panel de control
    Y ver un mensaje de bienvenida que contiene su nombre de usuario
    
  @Regression
  Esquema del escenario: Inicio de sesión con credenciales inválidas
    Dado que el usuario navega a la página de inicio de sesión
    Cuando el usuario ingresa "<usuario>" en el campo de usuario y "<contraseña>" en el de contraseña
    Y hace clic en el botón "Ingresar"
    Entonces el usuario debería ver un mensaje de error que dice "Credenciales inválidas"
    Pero no debería ser redirigido al panel de control

    Ejemplos:
      | usuario          | contraseña       |
      | usuario_invalido | clave_erronea    |
      | otro_usuario     | clave_incorrecta |
      |                  | contraseña_vacia |
