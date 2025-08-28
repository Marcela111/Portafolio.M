package com.empresa.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    // Los pasos usan la instancia del driver de la clase Hooks
    @Given("que el usuario navega a la página de inicio de sesión")
    public void navigate_to_login_page() {
        System.out.println("Navegando a la página de inicio de sesión...");
        Hooks.driver.get("hhttps://the-internet.herokuapp.com/"); // URL de ejemplo
    }

    // Este es el método corregido para capturar los parámetros del Scenario Outline
    @When("el usuario ingresa {string} en el campo de usuario y {string} en el de contraseña")
    public void user_enters_credentials(String username, String password) {
        System.out.printf("Ingresando usuario: %s y contraseña: %s%n", username, password);
        // Aquí iría la lógica de Selenium para encontrar elementos y enviar texto
        // Hooks.driver.findElement(By.id("username")).sendKeys(username);
        // Hooks.driver.findElement(By.id("password")).sendKeys(password);
    }

    @When("hace clic en el botón {string}")
    public void click_button(String buttonName) {
        System.out.printf("Haciendo clic en el botón: %s%n", buttonName);
        // Hooks.driver.findElement(By.id("login-button")).click();
    }

    @Then("el usuario debería ser redirigido al panel de control")
    public void user_should_be_redirected_to_dashboard() {
        System.out.println("Verificando la redirección al panel de control...");
        // assertTrue(Hooks.driver.getCurrentUrl().contains("/dashboard"));
    }

    @Then("ver un mensaje de bienvenida que contiene su nombre de usuario")
    public void user_sees_welcome_message() {
        System.out.println("Verificando mensaje de bienvenida...");
        // assertTrue(Hooks.driver.getPageSource().contains("Bienvenido, usuario_valido"));
    }

    @Then("el usuario debería ver un mensaje de error que dice {string}")
    public void user_sees_error_message(String errorMessage) {
        System.out.println("Verificando mensaje de error...");
        // assertTrue(Hooks.driver.getPageSource().contains(errorMessage));
    }

    @Then("no debería ser redirigido al panel de control")
    public void user_is_not_redirected() {
        System.out.println("Verificando que no se haya redirigido...");
        // assertFalse(Hooks.driver.getCurrentUrl().contains("/dashboard"));
    }

    @When("el usuario completa el formulario de registro con datos válidos")
    public void user_completes_registration_form_with_valid_data() {
        System.out.println("Completando formulario de registro con datos válidos...");
    }

    @Then("el usuario debería ver un mensaje de éxito que diga {string}")
    public void user_sees_success_message(String message) {
        System.out.println("Verificando mensaje de éxito en el registro...");
    }

    @Then("ser redirigido a la página de inicio de sesión")
    public void user_redirected_to_login_page() {
        System.out.println("Verificando redirección a la página de inicio de sesión...");
    }
}
