package com.future.springcloud.controller;

import com.future.springcloud.entities.CommonResult;
import com.future.springcloud.entities.Payment;
import com.future.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wsq
 * @date 2020/9/9  23:11
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(Payment payment)
    {
        int result = paymentService.create(payment);
        log.info("****插入结果："+ result);
        if(result > 0)
        {
            return new CommonResult(200,"插入成功",result);
        }
        else{
            return new CommonResult(444,"插入失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id)
    {
        Payment result = paymentService.getPaymentById(id);
        log.info("****插入结果："+ result);
        if(result != null)
        {
            return new CommonResult(200,"查询成功",result);
        }
        else{
            return new CommonResult(444,"查询失败",null);
        }
    }
}
