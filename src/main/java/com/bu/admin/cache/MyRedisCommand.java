package com.bu.admin.cache;


import com.bu.admin.redis.dao.IMyRedisDao;


public interface MyRedisCommand<T> {

    T execute(String key, IMyRedisDao myRedisDao);
}
