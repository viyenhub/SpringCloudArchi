package org.example.service;

import org.example.entity.CommonResult;
import org.example.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements  IPaymentService{

    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(44444,"服务降级返回，\t PaymentFallbackService",new Payment(id,"errorService"));
    }
}
