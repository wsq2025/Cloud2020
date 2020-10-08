package com.future.springcloud.controller;

import com.future.springcloud.distributed.GeneratorSnowflake;
import com.future.springcloud.entities.CommonResult;
import com.future.springcloud.entities.Payment;
import com.future.springcloud.entities.User;
import com.future.springcloud.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wsq
 * @date 2020/9/10  21:54
 */
@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private UserMapper userMapper;

    @Resource
    private GeneratorSnowflake snowflake;

    @GetMapping(value = "consumer/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/create",CommonResult.class);
    }


    @GetMapping(value = "/user/save")
    @ResponseBody
    public String save(){
        log.info("开始保存");
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        log.info("1288834974657L :" + sdf.format(new Date(1288834974657L)));
            User user = new User();

            user.setId(snowflake.SnowflakeId());
            log.info("生成id: "+ user.getId());
            user.setName("test");
            user.setCityId(1);
            user.setCreateTime(new Date());
            user.setSex(1 % 2 == 0 ? 1 : 2);
            user.setPhone("11111111");
            user.setEmail("xxxxx");
            user.setCreateTime(new Date());
            user.setPassword("eeeeeeeeeeee");
            userMapper.save(user);
        return "success";
    }

    @GetMapping(value = "/user/get/{id}")
    //@ResponseBody
    public User get(long id){
        return userMapper.get(id);
    }
}
