package com.bu.admin.cache;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;


public interface RedisCommand<T> {


    T execute(String key, RedisTemplate<String, String> redisTemplate, StringRedisTemplate stringRedisTemplate);
}
