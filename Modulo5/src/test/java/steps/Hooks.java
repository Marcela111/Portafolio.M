package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setUp() {
        System.out.println("----------------------------------------");
        System.out.println("[HOOK] Abriendo navegador...");
        
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        System.out.println("[HOOK] Cerrando navegador...");
        if (driver != null) {
            driver.quit();
        }
        System.out.println("----------------------------------------");
    }
}