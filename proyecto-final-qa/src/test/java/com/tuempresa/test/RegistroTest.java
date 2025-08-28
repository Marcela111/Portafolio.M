package com.tuempresa.tests;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tuempresa.pages.RegistroPage;
import com.tuempresa.utils.BaseTest;

public class RegistroTest extends BaseTest {
    private static final String REGISTER_URL = "https://demo.guru99.com/test/newtours/register.php";

    @Test(dataProvider = "registroDatos", description = "Valida el formulario de registro con diferentes datos.")
    public void testRegistro(String firstName, String lastName, String phone, String email, String password, String confirmPassword, boolean expectedResult) {
        getDriver().get(REGISTER_URL);
        RegistroPage registroPage = new RegistroPage(getDriver());
        registroPage.llenarFormulario(firstName, lastName, phone, email, password, confirmPassword);
        registroPage.clickSubmit();

        // Espera explícita para el cambio de URL
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        if (expectedResult) {
            // ¡CORRECCIÓN AQUÍ! Se cambió 'success' por 'sucess'
            wait.until(ExpectedConditions.urlContains("register_sucess.php"));
            Assert.assertTrue(getDriver().getCurrentUrl().contains("register_sucess.php"), "Fallo: El registro exitoso no fue validado.");
        } else {
            // Espera a que la URL *no* contenga la página de éxito
            try {
                // ¡CORRECCIÓN AQUÍ! Se cambió 'success' por 'sucess'
                wait.until(ExpectedConditions.urlContains("register_sucess.php"));
                Assert.fail("Fallo: El registro con datos inválidos fue exitoso inesperadamente.");
            } catch (Exception e) {
                // Si la URL de éxito no aparece en 10 segundos, asumimos que no fue exitoso, lo cual es lo esperado.
                // ¡CORRECCIÓN AQUÍ! Se cambió 'success' por 'sucess'
                Assert.assertFalse(getDriver().getCurrentUrl().contains("register_sucess.php"), "Fallo: El registro con datos inválidos fue exitoso.");
            }
        }
    }

    @DataProvider(name = "registroDatos")
    public Object[][] registroDatos() {
        return new Object[][] {
            // Datos válidos que el sitio web acepta
            {"Juan", "Perez", "1234567890", "juan.perez@test.com", "Pass1234!", "Pass1234!", true},
            {"Maria", "Gomez", "9876543210", "maria.gomez@test.com", "pass123", "pass123", true},
            {"", "Rodriguez", "1122334455", "rodrigo@test.com", "SecurePass!", "SecurePass!", true},
            {"Ana", "Lopez", "5566778899", "ana.lopez@test.com", "Pass123!", "Pass123!", true},
            {"Sofia", "Diaz", "1212121212", "", "TestPass123", "TestPass123", true},

            // Datos que el sitio web realmente rechaza (contraseñas no coinciden)
            {"Pedro", "Sanchez", "4455667788", "pedro.sanchez@test.com", "pass123!", "Pass1234!", false},
        };
    }
}