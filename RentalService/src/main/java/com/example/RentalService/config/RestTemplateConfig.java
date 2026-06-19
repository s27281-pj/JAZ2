package com.example.RentalService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        HttpClient httpClient = HttpClient.newHttpClient();
        return new RestTemplate(new JdkClientHttpRequestFactory(httpClient));
    }
}