package com.future.springcloud.service;

import com.future.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author wsq
 * @date 2020/9/9  23:05
 */
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
