package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApplicationPayment9003 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationPayment9003.class, args);
    }
}
