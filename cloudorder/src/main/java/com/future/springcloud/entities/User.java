package com.future.springcloud.entities;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wsq
 * @date 2020/10/7  14:12
 */
@Data
public class User implements Serializable {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String password;
    private Integer cityId;
    private Date createTime;
    private Integer sex;
}
