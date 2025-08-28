package com.tuempresa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // ¡CORRECCIÓN AQUI! Se cambia By.id("userName") a By.name("userName")
    private By usernameField = By.name("userName");
    
    // El localizador de la contraseña era correcto, pero lo dejamos como By.name por consistencia
    private By passwordField = By.name("password");
    
    // ¡CORRECCIÓN AQUI! Se cambia el localizador del botón de login a By.name("submit")
    private By loginButton = By.name("submit");
    
    // El mensaje de estado para login fallido
    private By statusMessage = By.xpath("//*[contains(text(),'Enter your userName and password')]"); // Mensaje cuando el login falla

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterUsername(String username) {
        WebElement userElement = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        userElement.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passElement.sendKeys(password);
    }

    public void clickLogin() {
        WebElement loginElement = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginElement.click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getStatusMessage() {
        // Espera a que el mensaje de estado sea visible
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(statusMessage));
        return messageElement.getText();
    }
}