import com.eci.arep.httpserver.HttpServer;
import com.eci.arep.httpserver.WorkoutPlanner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.net.*;
import java.util.List;

public class WorkoutTest {

    @Test
    void testGetWorkoutStrengthBeginner() {
        String[] workout = WorkoutPlanner.getWorkout("strength", "beginner");
        assertNotNull(workout, "La rutina no debe ser nula");
        assertEquals(3, workout.length, "Debe haber 3 ejercicios en la rutina");
    }

    @Test
    void testGetWorkoutCardioAdvanced() {
        String[] workout = WorkoutPlanner.getWorkout("cardio", "advanced");
        assertNotNull(workout, "La rutina no debe ser nula");
        assertTrue(workout[0].contains("Carrera de 10 km"), "El primer ejercicio debe ser 'Carrera de 10 km'");
    }

    @Test
    void testGetWorkoutFlexibilityIntermediate() {
        String[] workout = WorkoutPlanner.getWorkout("flexibility", "intermediate");
        assertNotNull(workout, "La rutina no debe ser nula");
        assertEquals(3, workout.length, "Debe haber 3 ejercicios en la rutina");
    }

    @Test
    void testGetWorkoutInvalidType() {
        String[] workout = WorkoutPlanner.getWorkout("unknown", "beginner");
        assertEquals(1, workout.length, "Debe devolver un solo mensaje");
        assertEquals("Rutina no encontrada.", workout[0], "Debe indicar que la rutina no se encontró");
    }

    @Test
    void testStaticFileServingIndex() throws IOException {
        URL url = new URL("http://localhost:8080/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        assertEquals(200, responseCode, "El servidor debe devolver código 200 para index.html");
    }

    @Test
    void testStaticFileServingCSS() throws IOException {
        URL url = new URL("http://localhost:8080/style.css");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        assertEquals(200, responseCode, "El servidor debe devolver código 200 para CSS");
        assertEquals("text/css", connection.getContentType(), "Debe devolver el tipo de contenido correcto");
    }

    @Test
    void testAPIWorkoutReturnsJSON() throws IOException {
        URL url = new URL("http://localhost:8080/api/workout?type=strength&level=beginner");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        assertEquals(200, responseCode, "El servidor debe devolver código 200 para la API");
        assertTrue(connection.getContentType().contains("application/json"), "Debe devolver JSON");
    }

    @Test
    void testInvalidAPIRequestReturns400() throws IOException {
        URL url = new URL("http://localhost:8080/api/workout");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        assertEquals(400, responseCode, "El servidor debe devolver 400 para una solicitud incorrecta");
    }

    @Test
    void testServerReturns404ForMissingFiles() throws IOException {
        URL url = new URL("http://localhost:8080/noexiste.html");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        assertEquals(404, responseCode, "El servidor debe devolver 404 para archivos inexistentes");
    }

    
}
