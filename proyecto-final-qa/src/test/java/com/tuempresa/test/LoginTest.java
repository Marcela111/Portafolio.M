package com.tuempresa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.tuempresa.pages.LoginPage;
import com.tuempresa.utils.BaseTest;

public class LoginTest extends BaseTest {
    private static final String LOGIN_URL = "https://demo.guru99.com/test/newtours/login.php";
    private static final String HOME_URL = "https://demo.guru99.com/test/newtours/index.php";

    @Test(description = "Verificar login con credenciales válidas")
    public void testLoginExitoso() {
        getDriver().get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("mercury", "mercury");
        
        // ¡CORRECCIÓN AQUÍ! Se cambió 'success' por 'sucess'
        Assert.assertTrue(getDriver().getCurrentUrl().contains("login_sucess"), "El login no fue exitoso.");
    }

    @Test(description = "Verificar login con credenciales inválidas")
    public void testLoginFallido() {
        getDriver().get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("usuarioinvalido", "passinvalido");
        
        // ¡CORRECCIÓN AQUÍ! Se cambió 'success' por 'sucess'
        Assert.assertFalse(getDriver().getCurrentUrl().contains("login_sucess"), "El login fue exitoso con credenciales inválidas.");
    }

    @Test(description = "Verificar comportamiento con múltiples fallas")
    public void testLoginFallidoMultiplesIntentos() {
        getDriver().get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(getDriver());
        
        loginPage.login("fail", "fail");
        // ¡CORRECCIÓN AQUÍ! Se cambió 'success' por 'sucess'
        Assert.assertFalse(getDriver().getCurrentUrl().contains("login_sucess"), "Intento 1 fallido.");

        loginPage.login("fail", "fail");
        // ¡CORRECCIÓN AQUÍ! Se cambió 'success' por 'sucess'
        Assert.assertFalse(getDriver().getCurrentUrl().contains("login_sucess"), "Intento 2 fallido.");

        loginPage.login("fail", "fail");
        // ¡CORRECCIÓN AQUÍ! Se cambió 'success' por 'sucess'
        Assert.assertFalse(getDriver().getCurrentUrl().contains("login_sucess"), "Intento 3 fallido.");
        
        // El sitio de prueba no tiene un bloqueo real, por lo que simplemente validamos que cada intento falle.
    }
}