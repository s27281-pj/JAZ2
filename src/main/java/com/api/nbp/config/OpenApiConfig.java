package com.api.nbp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OpenApiConfig {

    @Bean
    OpenAPI nbpOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("NBP exchange rates API")
                        .version("1.0.0")
                        .description("API wyliczajace sredni kurs waluty z ostatnich X dni na podstawie danych NBP."));
    }
}
