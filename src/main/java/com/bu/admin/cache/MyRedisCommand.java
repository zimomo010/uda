package com.bu.admin.cache;


import com.bu.admin.redis.dao.IMyRedisDao;

/**
 * 调用my redis dao层方法
 *
 * @author ghostWu
 * @date 2017/7/20
 */
public interface MyRedisCommand<T> {

    /**
     * 执行redis dao method
     * @param key
     * @param myRedisDao
     * @return
     */
    T execute(String key, IMyRedisDao myRedisDao);
}
