package org.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.example.entity.CommonResult;
import org.example.entity.Payment;
import org.example.service.IPaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class CircleBreakerController {

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private IPaymentService service;

    @GetMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback", fallback = "handlerException") //fallback 只负责业务异常
    //@SentinelResource(value="fallback",blockHandler = "blockHandle")//管Sentinel控制台的配置违规
    @SentinelResource(value = "fallback", fallback = "handlerException", blockHandler = "blockHandle",exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable("id") long id) {
        CommonResult<Payment> result = restTemplate.getForObject(serverURL + "/paymentSQL/" + id, CommonResult.class, id);

        if (id == 4) {
            throw new IllegalArgumentException("IllegalAccessException, 非法参数");
        } else if (result.getData() == null) {
            throw new NullPointerException("IllegalAccessException, 空指针异常");
        }

        return result;
    }

    public CommonResult handlerException(@PathVariable("id") long id, Throwable e) {
        Payment payment = new Payment(id, "null");
        return new CommonResult(444, "兜底異常處理 HandlerException:  "+ e.getMessage(), payment);
    }

    public CommonResult<Payment> blockHandle(@PathVariable("id") long id, BlockException exception) {
        Payment payment = new Payment(id, "null");
        return new CommonResult(445, "兜底異常處理 HandlerException: 无此ID " + id + "  block exception: " + exception.getMessage(), payment);
    }

    @GetMapping("/customer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return service.paymentSQL(id);
    }
}