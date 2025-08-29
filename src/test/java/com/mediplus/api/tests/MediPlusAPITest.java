package com.mediplus.api.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class MediPlusAPITest {

    private static final String BASE_URI = "http://api.mediplus.com";
    private static final String AUTH_TOKEN = "your_valid_auth_token"; // Reemplaza con un token real para pruebas.

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URI;
    }

    // --- Pruebas Funcionales CRUD (Lección 2) ---

    @Test(priority = 1, description = "Test GET /patients - Should return a list of patients with status 200")
    public void testGetPatients() {
        given()
            .header("Authorization", "Bearer " + AUTH_TOKEN)
        .when()
            .get("/patients")
        .then()
            .statusCode(200)
            .body("patients", notNullValue())
            .body("patients.size()", greaterThan(0))
            .time(lessThan(5000L));
    }

    @Test(priority = 2, description = "Test POST /patients - Should create a new patient with status 201")
    public void testPostPatient() {
        Map<String, String> patientData = new HashMap<>();
        patientData.put("name", "Juan Perez");
        patientData.put("dob", "1990-05-20");
        patientData.put("contact", "jperez@example.com");

        given()
            .header("Authorization", "Bearer " + AUTH_TOKEN)
            .contentType(ContentType.JSON)
            .body(patientData)
        .when()
            .post("/patients")
        .then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("name", equalTo("Juan Perez"))
            .time(lessThan(8000L));
    }

    @Test(priority = 3, description = "Test PUT /patients/{id} - Should update an existing patient with status 200")
    public void testPutPatient() {
        // Asumiendo que el paciente con ID 1 ya existe
        Map<String, String> updatedData = new HashMap<>();
        updatedData.put("contact", "new_contact@example.com");

        given()
            .header("Authorization", "Bearer " + AUTH_TOKEN)
            .contentType(ContentType.JSON)
            .body(updatedData)
        .when()
            .put("/patients/1")
        .then()
            .statusCode(200)
            .body("contact", equalTo("new_contact@example.com"))
            .time(lessThan(8000L));
    }

    @Test(priority = 4, description = "Test DELETE /patients/{id} - Should delete a patient with status 204")
    public void testDeletePatient() {
        // Asumiendo que el paciente con ID 2 existe
        given()
            .header("Authorization", "Bearer " + AUTH_TOKEN)
        .when()
            .delete("/patients/2")
        .then()
            .statusCode(204)
            .time(lessThan(5000L));
    }

    // --- Pruebas Negativas (Lección 2) ---

    @Test(priority = 5, description = "Negative Test: GET /patients/{id} with invalid ID - Should return status 404")
    public void testGetNonExistentPatient() {
        given()
            .header("Authorization", "Bearer " + AUTH_TOKEN)
        .when()
            .get("/patients/99999") // ID que no existe
        .then()
            .statusCode(404)
            .time(lessThan(5000L));
    }

    @Test(priority = 6, description = "Negative Test: POST /patients with invalid body - Should return status 400")
    public void testPostPatientWithInvalidData() {
        Map<String, String> invalidData = new HashMap<>();
        invalidData.put("name", "Juan"); // Falta 'contact' y 'dob'

        given()
            .header("Authorization", "Bearer " + AUTH_TOKEN)
            .contentType(ContentType.JSON)
            .body(invalidData)
        .when()
            .post("/patients")
        .then()
            .statusCode(400)
            .body("error", notNullValue())
            .time(lessThan(5000L));
    }

    // --- Pruebas de Seguridad (Lección 3) ---

    @Test(priority = 7, description = "Security Test: Request with valid API key/token - Should return status 200")
    public void testValidAuthToken() {
        given()
            .header("Authorization", "Bearer " + AUTH_TOKEN)
        .when()
            .get("/patients")
        .then()
            .statusCode(200);
    }

    @Test(priority = 8, description = "Security Test: Request with invalid API key/token - Should return status 401")
    public void testInvalidAuthToken() {
        given()
            .header("Authorization", "Bearer invalid_token")
        .when()
            .get("/patients")
        .then()
            .statusCode(401);
    }

    @Test(priority = 9, description = "Security Test: Request with no API key/token - Should return status 401")
    public void testNoAuthToken() {
        given()
        .when()
            .get("/patients")
        .then()
            .statusCode(401);
    }
}