package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ApplicationConfigCenter3344 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfigCenter3344.class, args);
    }
}
