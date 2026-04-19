package com.bu.admin.cache;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 调用my redis dao层方法
 *
 * @author ghostWu
 * @date 2017/7/20
 */
public interface RedisCommand<T> {

    /**
     * 执行redis dao method
     * @param key
     * @return
     */
    T execute(String key, RedisTemplate<String, String> redisTemplate, StringRedisTemplate stringRedisTemplate);
}
