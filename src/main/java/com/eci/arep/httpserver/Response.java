package com.eci.arep.httpserver;

import java.io.*;

public class Response {
    private final OutputStream out;

    public Response(OutputStream out) {
        this.out = out;
    }

    public void send(String response) throws IOException {
        out.write(response.getBytes());
        out.flush();
    }

    public void sendJson(String jsonResponse) throws IOException {
        String headers = "HTTP/1.1 200 OK\r\n"
                       + "Content-Type: application/json\r\n"
                       + "Content-Length: " + jsonResponse.getBytes().length + "\r\n"
                       + "\r\n";
    
        out.write(headers.getBytes());
        out.write(jsonResponse.getBytes());
        out.flush();
    }

    public void sendFile(byte[] fileContent, String contentType) throws IOException {
        String headers = "HTTP/1.1 200 OK\r\n"
                       + "Content-Type: " + contentType + "\r\n"
                       + "Content-Length: " + fileContent.length + "\r\n"
                       + "\r\n";
        out.write(headers.getBytes());
        out.write(fileContent);
        out.flush();
    }
}
