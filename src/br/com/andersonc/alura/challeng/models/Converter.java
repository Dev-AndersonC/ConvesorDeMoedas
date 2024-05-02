package br.com.andersonc.alura.challeng.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Converter {

    public double equals(double amount, String fromCurrency, String toCurrency) {
        // Obter a taxa de conversão entre as moedas
        double conversionRate = getConversion(fromCurrency, toCurrency);

        // Calcular o valor convertido
        double convertedAmount = amount * conversionRate;

        return convertedAmount;
    }

    private String apikey = "d30471e98c60fca42d737a47";

    private double getConversion(String fromCurrency, String toCurrency) {
        try {
            String url = "https://v6.exchangerate-api.com/v6/" + apikey + "/latest/" + fromCurrency;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jsonObj = new Gson().fromJson(response.body(), JsonObject.class);
            JsonObject conversionRatesObj = jsonObj.getAsJsonObject("conversion_rates");

            if (conversionRatesObj != null && conversionRatesObj.has(toCurrency)) {
                return conversionRatesObj.get(toCurrency).getAsDouble();
            } else {
                throw new IllegalArgumentException("Taxa de conversão não disponível para " + fromCurrency + " -> " + toCurrency);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Retorna -1 em caso de erro
            return -1;
        }

    }
}
