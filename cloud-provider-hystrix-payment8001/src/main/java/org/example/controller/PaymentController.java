package org.example.controller;

import com.oracle.tools.packager.Log;
import org.example.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PaymentController {

    @Resource
    private PaymentService service;

    @Value("server.port")
    private String serverPort;

    @GetMapping("/payment/hystrix/OK/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        return service.paymentInfo_OK(id);
    }

    @GetMapping("/payment/hystrix/Timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id) throws InterruptedException {

        String result = service.paymentInfo_Timeout(id);
        System.out.println("result:  " + result);
        return result;
    }

    //服務熔斷
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = service.paymentCircuitBreaker(id);
        Log.info("result: " + result);
        return result;
    }
}
