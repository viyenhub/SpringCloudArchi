package org.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.example.entity.CommonResult;
import org.example.entity.Payment;
import org.example.myhandler.CustomerExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value="byResource",blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200,"安资源名称限流",new Payment(2020L,"serial001"));
    }

    public CommonResult handleException(BlockException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用");
    }


    @GetMapping("/limitControl/byCustomerHandler")
    @SentinelResource(value="byCustomerHandler",
            blockHandlerClass = CustomerExceptionHandler.class,
            blockHandler = "myHandler02")
    public CommonResult byCustomerHandler(){
        return new CommonResult(200,"按资源名称限流",new Payment(2020L,"serial002"));
    }
}
