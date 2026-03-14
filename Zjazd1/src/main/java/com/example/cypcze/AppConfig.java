package com.example.cypcze;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public MyData myData(@Value("${my.custom.property:DEFAULT_VALUE}") String prop) {
        System.out.println("my.custom.property = " + prop);
        return new MyData("abc", 123);
    }

    @Bean(name = "defaultData")
    public List<String> defaultData() {
        return List.of("one", "two", "three", "four", "five");
    }
}