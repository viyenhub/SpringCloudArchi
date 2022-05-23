package org.example.controller;

import org.example.entity.CommonResult;
import org.example.entity.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();

    static {
        hashMap.put(1L, new Payment(1L, "78sd9f78sd7f8d7s9f7d89sf7ds97fs96fd6sd98f"));
        hashMap.put(2L, new Payment(2L, "v7sd43342sd76sdsdf8sdf8sd80s908fd9s8f9dsd"));
        hashMap.put(3L, new Payment(3L, "090782h1rjhj32h43j2hj3khjeqwhe3j1kh21j2h1"));
    }

    @GetMapping("paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult(200, "from mysql, server port:" + serverPort, payment);
        return result;
    }
}
