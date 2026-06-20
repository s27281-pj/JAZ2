package com.api.nbp.client;

import com.api.nbp.config.NbpProperties;
import com.api.nbp.exception.NbpApiException;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Component
public class NbpClient {

    private final HttpClient httpClient;
    private final NbpProperties nbpProperties;

    public NbpClient(NbpProperties nbpProperties) {
        this.nbpProperties = nbpProperties;
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();
    }

    public String getAverageRatesJson(String currency, LocalDate startDate, LocalDate endDate) {
        URI uri = UriComponentsBuilder.fromUriString(nbpProperties.baseUrl())
                .pathSegment("exchangerates", "rates", "a", currency, startDate.toString(), endDate.toString())
                .queryParam("format", "json")
                .build()
                .toUri();

        HttpRequest request = HttpRequest.newBuilder(uri)
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new NbpApiException(response.statusCode(), response.body());
            }
            return response.body();
        } catch (IOException exception) {
            throw new NbpApiException(503, "NBP API is unavailable");
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new NbpApiException(503, "NBP API request was interrupted");
        }
    }

    public record NbpResponse(String code, List<Rate> rates) {
    }

    public record Rate(BigDecimal mid) {
    }
}
