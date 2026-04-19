package com.bu.admin.cache;

import org.springframework.data.redis.connection.RedisConnection;

/**
 * redis pipeline函数
 *
 * @author ghostWu
 * @date 2017/7/21
 */
public interface MyRedisPipeline {

    /**
     * 执行
     * @param conn
     */
    void execute(RedisConnection conn);
}
