package com.tuempresa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistroPage {
    private WebDriver driver;

    // Localizadores de los elementos de la página de registro
    private By firstNameField = By.name("firstName");
    private By lastNameField = By.name("lastName");
    private By phoneField = By.name("phone");
    private By emailField = By.name("userName");
    private By passwordField = By.name("password");
    private By confirmPasswordField = By.name("confirmPassword");
    private By submitButton = By.name("submit");

    public RegistroPage(WebDriver driver) {
        this.driver = driver;
    }

    public void llenarFormulario(String firstName, String lastName, String phone, String email, String password, String confirmPassword) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(phoneField).sendKeys(phone);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
    }

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }
}