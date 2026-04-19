package com.bu.admin.cache;

import com.bu.admin.redis.dao.IMyRedisDao;

/**
 * 调用my redis dao层方法，无返回值
 *
 * @author ghostWu
 * @date 2017/7/24
 */
public interface MyRedisVoidCommand {

    /**
     * 执行redis dao method
     * @param key
     * @param myRedisDao
     * @return
     */
    void execute(String key, IMyRedisDao myRedisDao);
}
