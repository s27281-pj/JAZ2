package com.example.RentalService.config;

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
    public OpenAPI rentalServiceOpenAPI() {
        Server localServer = new Server()
                .url("http://localhost:8081")
                .description("Lokalny serwer RentalService");

        return new OpenAPI()
                .info(new Info()
                        .title("RentalService API")
                        .version("1.0")
                        .description("API pośredniczące między klientem a MovieService. Umożliwia pobieranie informacji o filmach, wypożyczanie filmów oraz ich zwracanie.")
                        .contact(new Contact()
                                .name("Cyprian")
                                .email("cyprian.czerwinski@gmail.com")))
                .servers(List.of(localServer));
    }
}