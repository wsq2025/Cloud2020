package com.future.springcloud.distributed;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author wsq
 * @date 2020/10/7  14:36
 */
@Configuration
public class GeneratorSnowflake {
    private long wokerId = 0L;
    private long datacenterId = 1;
    private Snowflake snowflake = IdUtil.createSnowflake(wokerId,datacenterId);

    @PostConstruct
    public void init()
    {
        try {
            wokerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            System.out.println("当前机器的workerId: " + wokerId);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("当前机器的wokerId获取失败：" + e);
            wokerId = NetUtil.getLocalhostStr().hashCode();
        }

    }

    public synchronized long SnowflakeId()
    {
        return snowflake.nextId();
    }

    public synchronized long SnowflakeId(long wokerId,long datacnterId)
    {
        Snowflake snowflake = IdUtil.createSnowflake(wokerId,datacenterId);
        return snowflake.nextId();
    }

    public static void main(String[] args) {
        System.out.println(new GeneratorSnowflake().SnowflakeId());
    }
}
