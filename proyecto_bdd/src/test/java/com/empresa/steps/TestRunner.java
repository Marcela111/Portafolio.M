package com.empresa.steps;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    // Define la ubicación de los archivos .feature
    features = "src/test/resources/features",

    // Especifica el paquete donde se encuentran las Step Definitions y los Hooks
    glue = {"com.empresa.steps"},

    // Define los plugins de reporte.
    // Se recomienda generar el JSON para luego crear un reporte HTML más completo.
    plugin = {
        "pretty",
        "json:target/cucumber-reports/reporte.json",
        "html:target/cucumber-reports/reporte.html"
    },

    // Filtra los escenarios a ejecutar por etiquetas.
    // Puedes usar @nombreTag o "not @nombreTag" para excluir tags.
    // Por ejemplo: tags = "@SmokeTest and not @WIP"
    tags = "@Regression",

    // Muestra los nombres de los pasos de una forma más legible y limpia.
    monochrome = true,

    // Verifica que todos los pasos de los archivos .feature tengan una Step Definition.
    // Si se establece en true, detiene la ejecución si falta alguna definición.
    dryRun = false
)
public class TestRunner {
    // Esta clase sigue siendo el punto de entrada para ejecutar las pruebas con JUnit.
    // No necesita código adicional.
}