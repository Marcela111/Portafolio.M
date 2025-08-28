package com.empresa.steps;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

public class CrudSteps {

    // Instancia del WebDriver compartida
    private WebDriver driver;

    // Métodos para los pasos ya implementados
    @Dado("que el administrador ha iniciado sesión")
    public void admin_is_logged_in() {
        // Lógica para simular el inicio de sesión del administrador
        // Por ejemplo, navegar a la URL de login, ingresar credenciales y hacer clic
        System.out.println("Simulando inicio de sesión de administrador...");
    }

    @Cuando("el administrador navega a la página de {string}")
    public void admin_navigates_to_page(String page) {
        // Lógica para navegar a la página especificada
        System.out.println("Navegando a la página de: " + page);
    }

    @Cuando("completa el formulario de producto con detalles válidos")
    public void completes_product_form() {
        // Lógica para llenar el formulario de creación de producto
        System.out.println("Completando formulario de producto con detalles válidos...");
    }

    @Entonces("el nuevo producto {string} debería aparecer en la lista")
    public void new_product_should_appear_in_list(String productName) {
        // Lógica para verificar que el producto aparece en la lista de la UI
        System.out.println("Verificando que " + productName + " aparece en la lista...");
    }

    @Entonces("ver un mensaje de confirmación que dice {string}")
    public void see_confirmation_message(String message) {
        // Lógica para verificar el mensaje de confirmación
        System.out.println("Verificando mensaje de confirmación: " + message);
        // Ejemplo de validación:
        // WebElement messageElement = driver.findElement(By.id("confirm-message"));
        // assertEquals(message, messageElement.getText());
    }
    
    //---------------------------------------------------------
    // Nuevos métodos para los escenarios de "Modificación" y "Eliminación"
    //---------------------------------------------------------

    @Cuando("selecciona el producto {string} para modificar")
    public void selecciona_el_producto_para_modificar(String productName) {
        // Lógica de Selenium para encontrar y hacer clic en el botón de edición
        // del producto con el nombre proporcionado.
        System.out.println("Seleccionando el producto " + productName + " para modificar.");
    }

    @Cuando("actualiza el campo de precio a {string}")
    public void actualiza_el_campo_de_precio_a(String newPrice) {
        // Lógica de Selenium para encontrar el campo de precio y actualizar su valor.
        System.out.println("Actualizando el precio a: " + newPrice);
    }

    @Entonces("el producto {string} debería mostrar el precio {string} en la lista")
    public void el_producto_debería_mostrar_el_precio_en_la_lista(String productName, String expectedPrice) {
        // Lógica de Selenium para encontrar el producto en la lista y verificar
        // que su precio se ha actualizado correctamente.
        System.out.println("Verificando que " + productName + " muestra el precio " + expectedPrice + ".");
    }

    @Cuando("selecciona el producto {string} para eliminar")
    public void selecciona_el_producto_para_eliminar(String productName) {
        // Lógica de Selenium para encontrar y hacer clic en el botón de eliminación
        // del producto con el nombre proporcionado.
        System.out.println("Seleccionando el producto " + productName + " para eliminar.");
    }

    @Cuando("confirma la eliminación en el cuadro de diálogo")
    public void confirma_la_eliminación_en_el_cuadro_de_diálogo() {
        // Lógica de Selenium para encontrar y hacer clic en el botón de confirmación
        // en el cuadro de diálogo de eliminación.
        System.out.println("Confirmando la eliminación en el cuadro de diálogo.");
    }

    @Entonces("el producto {string} no debería aparecer en la lista")
    public void el_producto_no_debería_aparecer_en_la_lista(String productName) {
        // Lógica de Selenium para verificar que el producto ya no está presente
        // en la lista de la UI.
        System.out.println("Verificando que " + productName + " no aparece en la lista.");
    }

}
