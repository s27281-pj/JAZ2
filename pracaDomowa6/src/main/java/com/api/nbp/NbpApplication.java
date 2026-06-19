package com.api.nbp;

import com.api.nbp.config.NbpProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(NbpProperties.class)
public class NbpApplication {

	public static void main(String[] args) {
		SpringApplication.run(NbpApplication.class, args);
	}

}
