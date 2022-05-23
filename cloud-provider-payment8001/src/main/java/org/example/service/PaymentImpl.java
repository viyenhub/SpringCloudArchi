package org.example.service;

import org.example.dao.PaymentDao;
import org.example.entity.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentImpl {

    @Resource
    PaymentDao paymentDao;

    public int create(Payment payment){
        return paymentDao.create(payment);
    }
    public Payment getPaymentById(long id) {
        return paymentDao.getPaymentById(id);
    }

}
