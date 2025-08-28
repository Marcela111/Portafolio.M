package com.tuempresa.tests;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.tuempresa.pages.LoginPage;
import com.tuempresa.pages.ReservaPage;
import com.tuempresa.utils.BaseTest;

public class ReservaTest extends BaseTest {
    private static final String LOGIN_URL = "https://demo.guru99.com/test/newtours/login.php";

    @Test(description = "Validar la reserva de vuelos de solo ida")
    public void testReservaVueloSoloIda() {
        getDriver().get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("mercury", "mercury");
        
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("login_sucess.php"));

        ReservaPage reservaPage = new ReservaPage(getDriver());
        reservaPage.seleccionarTipoDeVuelo("solo ida");
        reservaPage.seleccionarPasajeros("2");
        reservaPage.seleccionarOrigen("London");
        reservaPage.seleccionarDestino("Sydney");
        reservaPage.clickBuscarVuelos();

        Assert.assertTrue(getDriver().getTitle().contains("Select a Flight"), "Fallo: No se redirigió a la página de selección de vuelos.");
    }
}