package org.example.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.example.service.IPaymentHystrixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@DefaultProperties(defaultFallback = "payment_global_fallbackmethod")
public class OrderHystrixController {

    @Resource
    private IPaymentHystrixService service;

    @GetMapping("/consumer/payment/hystrix/OK/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result= service.paymentInfo_OK(id);
        return result;
    }

//    @GetMapping("/consumer/payment/hystrix/Timeout/{id}")
//    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
//        String result= service.paymentInfo_Timeout(id);
//        return result;
//    }


    @GetMapping("/consumer/payment/hystrix/Timeout/{id}")
    //运行异常或者超时都会走兜底的方法 paymentInfo_Timeout_Handler
//    @HystrixCommand(fallbackMethod = "paymentInfo_Timeout_Handler",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @HystrixCommand
    public String paymentInfo_Timeout(Integer id) throws InterruptedException {

        String result= service.paymentInfo_Timeout(id);
        return result;
    }

    public String paymentInfo_Timeout_Handler(Integer id) {
        return Thread.currentThread().getName()+"\t  客户端系统服务正忙，请稍后再试。";
    }

    public String payment_global_fallbackmethod (){
        return Thread.currentThread().getName()+"\t  客户端全局服务正忙，请稍后再试。";
    }
}
