package com.eci.arep.httpserver;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.Map;

public class HttpServer {

    private static final String STATIC_FOLDER = "src/main/resources/static"; // Ajuste en la ruta de archivos estáticos

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Servidor iniciado en el puerto 8080...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            handleRequest(clientSocket);
        }
    }

    private static void handleRequest(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        OutputStream out = clientSocket.getOutputStream();
        String inputLine, filePath = "";
        boolean isFirstLine = true;

        while ((inputLine = in.readLine()) != null && !inputLine.isEmpty()) {
            if (isFirstLine) {
                filePath = inputLine.split(" ")[1];
                isFirstLine = false;
            }
            System.out.println("Received: " + inputLine);
        }

        if (filePath.startsWith("/api/workout")) {
            handleWorkoutRequest(out, filePath);
        } else {
            serveStaticFile(out, filePath);
        }

        out.close();
        in.close();
        clientSocket.close();
    }

    private static void handleWorkoutRequest(OutputStream out, String filePath) throws IOException {
        try {
            String[] parts = filePath.split("\\?");
            if (parts.length < 2) throw new IllegalArgumentException("Parámetros inválidos");

            String query = parts[1];
            Map<String, String> params = parseQuery(query);

            String type = params.get("type");
            String level = params.get("level");

            String[] workout = WorkoutPlanner.getWorkout(type, level);

            StringBuilder jsonResponse = new StringBuilder("HTTP/1.1 200 OK\r\nContent-Type: application/json\r\n\r\n{");
            jsonResponse.append("\"workout\":[");
            for (int i = 0; i < workout.length; i++) {
                jsonResponse.append("\"").append(workout[i]).append("\"");
                if (i < workout.length - 1) {
                    jsonResponse.append(",");
                }
            }
            jsonResponse.append("]}");

            out.write(jsonResponse.toString().getBytes());
        } catch (Exception e) {
            out.write("HTTP/1.1 400 Bad Request\r\n\r\nError en la solicitud".getBytes());
        }
    }

    private static void serveStaticFile(OutputStream out, String filePath) throws IOException {
        if (filePath.equals("/")) filePath = "/index.html"; // Redirigir raíz al index.html

        // Construir ruta correcta
        Path file = Paths.get(STATIC_FOLDER, filePath).toAbsolutePath();
        System.out.println("Buscando archivo en: " + file.toString());

        if (Files.exists(file) && !Files.isDirectory(file)) {
            String contentType = getContentType(filePath);
            byte[] fileContent = Files.readAllBytes(file);
            out.write(("HTTP/1.1 200 OK\r\nContent-Type: " + contentType + "\r\n\r\n").getBytes());
            out.write(fileContent);
        } else {
            out.write("HTTP/1.1 404 Not Found\r\n\r\nArchivo no encontrado.".getBytes());
        }
    }

    private static String getContentType(String filePath) {
        if (filePath.endsWith(".html")) return "text/html";
        if (filePath.endsWith(".css")) return "text/css";
        if (filePath.endsWith(".js")) return "application/javascript";
        if (filePath.endsWith(".png")) return "image/png";
        if (filePath.endsWith(".jpg") || filePath.endsWith(".jpeg")) return "image/jpeg";
        return "text/plain";
    }

    private static Map<String, String> parseQuery(String query) {
        Map<String, String> params = new java.util.HashMap<>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                params.put(keyValue[0], keyValue[1]);
            }
        }
        return params;
    }
}
