package org.example.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements IPaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return Thread.currentThread().getName()+"\t  paymentInfo_OK 客户端系统服务正忙，请稍后再试。";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return Thread.currentThread().getName()+"\t paymentInfo_Timeout 客户端系统服务正忙，请稍后再试。";
    }
}
