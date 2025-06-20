package com.alura.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    public String obtenerDatos(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Código de estado: " + response.statusCode());
            System.out.println("Encabezados: " + response.headers());
            System.out.println("Cuerpo: " + response.body());

            if (response.statusCode() != 200) {
                System.out.println("La API devolvió un error HTTP.");
                return null;
            }

            return response.body();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al hacer la solicitud HTTP: " + e.getMessage());
        }
    }
}

