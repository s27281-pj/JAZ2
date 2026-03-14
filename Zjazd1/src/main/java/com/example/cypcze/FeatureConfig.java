package com.example.cypcze;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeatureConfig {

    @Bean
    @ConditionalOnProperty(prefix = "my.feature", name = "enabled", havingValue = "true")
    public FeatureBean featureBean() {
        return new FeatureBean();
    }
}