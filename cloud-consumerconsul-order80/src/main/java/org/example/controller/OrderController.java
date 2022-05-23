package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {

    private static final String INVOKE_URL = "http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value ="/consumer/payment/consul")
    public String getPayment() {
        String URL = restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);

        return URL;
    }
}
