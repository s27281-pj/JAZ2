package com.example.cypcze;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@SpringBootApplication
@ImportResource("classpath:beans.xml")
public class CypczeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CypczeApplication.class, args);
    }

    @Bean
    ContextUser contextUser(ApplicationContext ctx) {
        return new ContextUser(ctx);
    }

    @Bean
    CommandLineRunner run(ComponentA a,
                          ComponentB b,
                          ContextUser user,
                          MyData data,
                          @Qualifier("defaultData") List<String> list) {

        return args -> {
            System.out.println(a.name());
            System.out.println(b.name());
            user.makeBeansAndCall();

            System.out.println(data);
            System.out.println(list);
        };
    }
}