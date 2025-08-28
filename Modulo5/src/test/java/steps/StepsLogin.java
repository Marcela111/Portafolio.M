package steps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepsLogin {
    
    public WebDriver driver = Hooks.driver;

    @Given("el usuario está en la página de login")
    public void usuarioEnLogin() {
        System.out.println("[STEP] Navegando a la página de login...");
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @When("ingresa {string} y {string}")
    public void ingresarCredenciales(String usuario, String password) {
        System.out.printf("[STEP] Ingresando credenciales: usuario=%s, password=%s%n", usuario, password);
        driver.findElement(By.id("username")).sendKeys(usuario);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("se muestra un mensaje de éxito")
    public void se_muestra_un_mensaje_de_exito() {
        System.out.println("[STEP] Verificando mensaje de éxito.");
        String mensaje = driver.findElement(By.id("flash")).getText();
        Assert.assertTrue(mensaje.contains("You logged into a secure area!"));
    }

    @Then("se muestra un mensaje de error {string}")
    public void se_muestra_el_mensaje_de_error(String mensajeEsperado) {
        System.out.printf("[STEP] Validando mensaje de error: %s%n", mensajeEsperado);
        String mensajeReal = driver.findElement(By.id("flash")).getText();
        Assert.assertTrue(mensajeReal.contains(mensajeEsperado));
    }
}