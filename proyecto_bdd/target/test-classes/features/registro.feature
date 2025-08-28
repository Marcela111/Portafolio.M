#language: es
@Registro @HU-002 @SmokeTest
Característica: Proceso de registro de nuevos usuarios
  Para obtener acceso completo a la plataforma
  Como un usuario no registrado
  Quiero crear una nueva cuenta

  Escenario: Registro exitoso de un nuevo usuario con datos válidos
    Dado que el usuario navega a la página de registro
    Cuando el usuario completa el formulario de registro con datos válidos
    Y hace clic en el botón "Registrarse"
    Entonces el usuario debería ver un mensaje de éxito que diga "Registro completado"
    Y ser redirigido a la página de inicio de sesión

  @WIP
  Escenario: Registro fallido por contraseña débil
    Dado que el usuario navega a la página de registro
    Cuando el usuario completa el formulario de registro con un usuario y una contraseña débil
    Y hace clic en el botón "Registrarse"
    Entonces el usuario debería ver un mensaje de error que indique "La contraseña no cumple los requisitos de seguridad"
    Pero no debería ser redirigido a la página de inicio de sesión
