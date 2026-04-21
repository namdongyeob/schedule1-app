package com.example.schedule1app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Schedule1AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(Schedule1AppApplication.class, args);
    }

}
