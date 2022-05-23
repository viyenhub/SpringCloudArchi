package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class ApplicationDashboard9001 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationDashboard9001.class, args);
    }


}
