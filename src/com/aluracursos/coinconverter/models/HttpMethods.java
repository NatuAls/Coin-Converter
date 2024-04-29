package com.aluracursos.coinconverter.models;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpMethods {
    public ExchangeRate get(String baseCoin, String targetCoin, double amount){
        var apiKey = "841b0872b3d44a326bf46cc3";

        var uri = "https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/%f"
                .formatted(apiKey, baseCoin, targetCoin, amount);

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return new Gson().fromJson(response.body(), ExchangeRate.class);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}