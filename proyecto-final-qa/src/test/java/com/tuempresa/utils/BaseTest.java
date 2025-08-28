package com.tuempresa.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    // Utiliza ThreadLocal para que cada hilo de ejecución tenga su propia instancia de WebDriver
    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    // Método para obtener el driver de forma segura desde cualquier hilo
    public WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) {
        WebDriver driver = null;
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            // Manejo de error si se especifica un navegador no soportado
            throw new IllegalArgumentException("El navegador " + browser + " no es soportado.");
        }

        driver.manage().window().maximize();
        // Configuración de timeouts globales
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        threadLocalDriver.set(driver); // Guarda la instancia del driver en el hilo actual
    }

    @AfterMethod
    public void teardown() {
        if (getDriver() != null) {
            getDriver().quit();
            threadLocalDriver.remove(); // Limpia la instancia del driver del hilo
        }
    }
}