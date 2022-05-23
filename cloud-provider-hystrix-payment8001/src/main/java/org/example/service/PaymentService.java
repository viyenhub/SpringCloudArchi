package org.example.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id) {
        return Thread.currentThread().getName() + "\t  paymentInfo_OK ID " + id;
    }

    //运行异常或者超时都会走兜底的方法 paymentInfo_Timeout_Handler
    @HystrixCommand(fallbackMethod = "paymentInfo_Timeout_Handler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_Timeout(Integer id) throws InterruptedException {

        //TimeUnit.SECONDS.sleep(3);
        int k = 10 / 0;
        return Thread.currentThread().getName() + "\t  paymentInfo_Timeout ID " + id;
    }

    public String paymentInfo_Timeout_Handler(Integer id) {
        return Thread.currentThread().getName() + "\t  服务正忙，请稍后再试。";
    }


    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),//失败效率达多少后熔断
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("###$$$$$$$ id 不能是负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t 调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能是负数，请稍后再试 o(╥﹏╥)o id: "+id;
    }
}
