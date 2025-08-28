Feature: Login
  Para verificar que el usuario pueda iniciar sesión correctamente

  Scenario: Inicio de sesión válido
    Given el usuario está en la página de login
    When ingresa "tomsmith" y "SuperSecretPassword!"
    Then se muestra un mensaje de éxito

  Scenario: Inicio de sesión con credenciales inválidas
    Given el usuario está en la página de login
    When ingresa "usuario_erroneo" y "password_invalido"
    Then se muestra un mensaje de error "Your username is invalid!"