package com.example.MovieService.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI movieServiceOpenAPI() {
        Server localServer = new Server()
                .url("http://localhost:8080")
                .description("Lokalny serwer MovieService");

        return new OpenAPI()
                .info(new Info()
                        .title("MovieService API")
                        .version("1.0")
                        .description("API do zarządzania filmami oraz ich dostępnością.")
                        .contact(new Contact()
                                .name("Cyprian")
                                .email("cyprian.czerwinski@gmail.com")))
                .servers(List.of(localServer));
    }
}