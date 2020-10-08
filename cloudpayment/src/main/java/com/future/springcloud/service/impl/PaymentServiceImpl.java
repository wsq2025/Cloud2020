package com.future.springcloud.service.impl;

import com.future.springcloud.dao.PaymentDao;
import com.future.springcloud.entities.Payment;
import com.future.springcloud.service.PaymentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wsq
 * @date 2020/9/9  23:08
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment)
    {
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(@Param("id") Long id)
    {
        return paymentDao.getPaymentById(id);
    }
}
