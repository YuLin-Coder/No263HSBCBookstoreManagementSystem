package com.beauty.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

/**
 * Redis 配置，保存支付订单号
 */
@Configuration
public class JedisConfig {

    @Bean
    public Jedis jedis(){
        Jedis jedis = new Jedis("",6379);
        return jedis;
    }
}
