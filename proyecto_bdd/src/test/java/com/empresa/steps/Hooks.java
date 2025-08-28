package com.empresa.steps;

import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setup() {
        // Inicializa WebDriverManager y el driver de Chrome
        WebDriverManager.chromedriver().setup();

        // Configura ChromeOptions para permitir el origen remoto y evitar el error de conexión
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        
        // Crea una nueva instancia de ChromeDriver con las opciones configuradas
        driver = new ChromeDriver(options);
        
        // Configura el tiempo de espera implícito y maximiza la ventana del navegador
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        System.out.println("--- Inicializando el navegador para el escenario ---");
    }

    @After
    public void tearDown(Scenario scenario) {
        // Verifica si el escenario falló
        if (scenario.isFailed()) {
            System.out.println("--- El escenario ha fallado. Tomando captura de pantalla ---");
            // Asegúrate de que el driver no sea null antes de tomar la captura
            if (driver != null) {
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            }
        }
        
        // Cierra el navegador si el driver no es null
        if (driver != null) {
            driver.quit();
            System.out.println("--- Cerrando el navegador ---");
        }
    }
}
