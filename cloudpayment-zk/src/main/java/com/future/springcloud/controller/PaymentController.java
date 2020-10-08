package com.future.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author wsq
 * @date 2020/9/21  0:51
 */
@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    public String server_port;

    @GetMapping(value = "payment/zk")
    public String paymentzk(){
        return "springcloud with zookeeper :" + server_port + UUID.randomUUID().toString();
    }
}
