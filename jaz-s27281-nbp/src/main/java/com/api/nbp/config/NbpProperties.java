package com.api.nbp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "nbp.api")
public record NbpProperties(String baseUrl) {
}
