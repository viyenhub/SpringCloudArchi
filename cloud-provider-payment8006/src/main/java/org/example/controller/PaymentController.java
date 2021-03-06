package org.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;


    @RequestMapping(value = "/payment/consul")
    public String paymentConsul() {
        return "SpringCloud with Consul: " + serverPort + "\t" + UUID.randomUUID().toString();
    }

}
