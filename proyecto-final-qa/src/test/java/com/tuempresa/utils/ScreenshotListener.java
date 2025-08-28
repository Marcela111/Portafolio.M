package com.tuempresa.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotListener implements ITestListener {

    private void takeScreenshot(ITestResult result) {
        // Asegúrate de que el objeto del test sea una instancia de BaseTest
        Object testInstance = result.getInstance();
        if (!(testInstance instanceof BaseTest)) {
            System.err.println("No se puede obtener el driver. La clase de prueba no extiende BaseTest.");
            return;
        }

        WebDriver driver = ((BaseTest) testInstance).getDriver();
        if (driver instanceof TakesScreenshot) {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
            String testName = result.getMethod().getMethodName();
            String fileName = testName + "_" + timestamp + ".png";
            
            Path screenshotsPath = Paths.get("target", "surefire-reports", "screenshots");
            try {
                if (!Files.exists(screenshotsPath)) {
                    Files.createDirectories(screenshotsPath);
                }
                Files.copy(screenshotFile.toPath(), screenshotsPath.resolve(fileName));
                System.out.println("Captura guardada: " + screenshotsPath.resolve(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test fallido: " + result.getName());
        takeScreenshot(result);
    }
}