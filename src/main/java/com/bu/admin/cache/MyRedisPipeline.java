package com.bu.admin.cache;

import org.springframework.data.redis.connection.RedisConnection;


public interface MyRedisPipeline {


    void execute(RedisConnection conn);
}
