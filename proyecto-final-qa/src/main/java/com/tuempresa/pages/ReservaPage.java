package com.tuempresa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReservaPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By oneWayRadioButton = By.cssSelector("input[value='oneway']");
    private By passengersSelect = By.name("passCount");
    private By fromPortSelect = By.name("fromPort");
    private By toPortSelect = By.name("toPort");
    private By findFlightsButton = By.name("findFlights");

    public ReservaPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void seleccionarTipoDeVuelo(String tipo) {
        if (tipo.equalsIgnoreCase("solo ida")) {
            wait.until(ExpectedConditions.elementToBeClickable(oneWayRadioButton)).click();
        }
    }
    
    public void seleccionarPasajeros(String count) {
        WebElement selectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passengersSelect));
        Select select = new Select(selectElement);
        select.selectByVisibleText(count);
    }

    public void seleccionarOrigen(String port) {
        WebElement selectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(fromPortSelect));
        Select select = new Select(selectElement);
        select.selectByVisibleText(port);
    }

    public void seleccionarDestino(String port) {
        WebElement selectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(toPortSelect));
        Select select = new Select(selectElement);
        select.selectByVisibleText(port);
    }

    public void clickBuscarVuelos() {
        wait.until(ExpectedConditions.elementToBeClickable(findFlightsButton)).click();
    }
}