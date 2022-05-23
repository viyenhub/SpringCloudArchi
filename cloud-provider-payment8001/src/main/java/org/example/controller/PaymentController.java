package org.example.controller;


import com.oracle.tools.packager.Log;
import org.example.entity.CommonResult;
import org.example.entity.Payment;
import org.example.service.PaymentImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class PaymentController {

    @Resource
    PaymentImpl paymentImpl;

    @Value("${server.port}")
    private String servicePort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(Payment payment) {
        int result = paymentImpl.create(payment);

        return result > 0 ? new CommonResult(0, "Success, port: " + servicePort, result) : new CommonResult(-1, "Fail", null);
    }


    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentImpl.getPaymentById(id);
        return payment != null ? new CommonResult(0, "Success, part: " + servicePort, payment) : new CommonResult(-1, "Fail", null);
    }

    @GetMapping("/payment/discovery")
    public Object getDiscoveryService() {

        List<String> services = discoveryClient.getServices();
        for (String item : services) {
            Log.info("Service: " + item);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        for (ServiceInstance item : instances) {
            Log.info(item.getInstanceId() + "\t" + item.getHost() + "\t" + item.getPort() + "\t" + item.getUri());
        }
        return discoveryClient;
    }

    @GetMapping("/payment/port")
    public String getPort(){
        return servicePort;
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return servicePort;
    }

    @GetMapping("payment/zipkin")
    public String paymentZipkin(){
        return "hello zipkin server.";
    }
}
