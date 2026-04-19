package com.bu.admin.redis.service;

import com.bu.admin.redis.dao.IMyRedisDao;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 业务层操作Redis
 *
 * @author ghostWu
 */
@Service("myRediService")
public class MyRediService {

    @Resource
    private IMyRedisDao myRedisDao;

    /**
     * 通过key删除
     *
     * @param keys
     */
    public long deleteByKey(String... keys) {
        return myRedisDao.del(keys);
    }

    /**
     * 添加Value To Sorted Set
     *
     * @param key
     * @param value
     * @param score
     * @return
     */
    public Boolean add(String key, String value, double score) {
        return myRedisDao.add(key, value, score);
    }

    /**
     * 根据KEY从Sorted Set中获取值
     *
     * @param key
     * @return
     */
    public Set<String> getAllSetByKey(String key) {
        return myRedisDao.rangeSet(key, 0, myRedisDao.size(key));
    }

    /**
     * set
     *
     * @param key, field, value
     * @return
     */
    public void hset(String key, String field, Object value) {
        myRedisDao.hset(key, field, value);
    }

    public boolean hsetnx(String key, String field, Object value) {
        return myRedisDao.hsetnx(key, field, value);
    }

    /**
     * delete
     *
     * @param key , field
     * @return
     */
    public void hdel(String key, String field) {
        myRedisDao.hdel(key, field);
    }

    /**
     * hgetAll
     *
     * @param key
     * @return
     */
    public Map<String, Object> hgetAll(String key) {
        return myRedisDao.hgetAll(key);
    }

    public void hmset(String key, Map<String, Object> map) {
        myRedisDao.hmset(key, map);
    }

    /**
     * hget
     *
     * @param key
     * @param field
     * @return
     */
    public Object hget(String key, String field) {
        return myRedisDao.hget(key, field);
    }

    /**
     * 对map指定field增加value,并返回增加后的值
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hincrBy(String key, String field, Long value) {
        return myRedisDao.hincrBy(key, field, value);
    }

    public Long hlen(String key) {
        return myRedisDao.hlen(key);
    }

    /**
     * key过期时间设置
     *
     * @param key
     * @param time
     * @param unit
     * @return
     */
    public Boolean expireTime(String key, long time, TimeUnit unit) {
        return myRedisDao.expireTime(key, time, unit);
    }

    /**
     * 添加key value 并且设置存活时间
     *
     * @param key
     * @param value
     * @param liveTime 单位秒
     */
    public void set(String key, String value, long liveTime) {
        myRedisDao.set(key, value, liveTime);
    }

    /**
     * 添加key value
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        myRedisDao.set(key, value);
    }

    /**
     * 获取redis value (String)
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return myRedisDao.get(key);
    }

    public Long push(String key, String value) {
        return myRedisDao.push(key, value);
    }

    public Long pushOfRight(String key,String value){
        return myRedisDao.pushRight(key,value);
    }
    public String pop(String key) {
        return myRedisDao.pop(key);
    }

    /**
     * 在list队列尾添加一个值
     *
     * @param key
     * @param value
     * @return
     */
    public Long rightPush(String key, String value) {
        return myRedisDao.in(key, value);
    }

    public String bpop(String key, long timeout, TimeUnit unit) {
        return myRedisDao.bpop(key, timeout, unit);
    }

    public Long length(String key) {
        return myRedisDao.length(key);
    }

    public List<String> range(String key, long start, long end) {
        return myRedisDao.range(key, start, end);
    }

    public void set(String key, long index, String value) {
         myRedisDao.set(key, index, value);
    }

    /**
     * 获取key对应的值类型
     *
     * @param key
     * @return
     */
    public String getType(String key) {
        return myRedisDao.getType(key);
    }

    public boolean exists(String key) {
        return myRedisDao.exists(key);
    }

    /**
     * 获取锁
     *
     * @param lockName
     * @param expire
     * @return
     */
    public Boolean acquireLock(String lockName, long expire) {
        return myRedisDao.acquireLock(lockName, expire);
    }

    /**
     * 释放锁
     *
     * @param lockName
     */
    public void releaseLock(String lockName) {
        myRedisDao.releaseLock(lockName);
    }

    /**
     * 对key进行自增/自减1
     *
     * @param key
     * @param delta 1：自增；-1：自减
     * @return
     */
    public Long increment(String key, long delta) {
        return myRedisDao.increment(key, delta);
    }

    /**
     * hash field是否存在
     *
     * @param key
     * @param field
     * @return
     */
    public Boolean hexists(String key, String field) {
        return myRedisDao.hexists(key, field);
    }

    /**
     * 添加 Value To Sorted set
     *
     * @param key
     * @param value
     * @param score
     * @return
     */
    public Boolean addToZSet(String key, String value, double score) {
        return myRedisDao.add(key, value, score);
    }

    /**
     * 根据Key从Sorted Set中查找指定范围的Value
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> rangeFromZSet(String key, long start, long end) {
        return myRedisDao.rangeSet(key, start, end);
    }

    /**
     * 根据Key，删除Sorted Set中指定范围的Value
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Long removeRangeFromZSet(String key, long start, long end) {
        return myRedisDao.removeRange(key, start, end);
    }

    /**
     * 查询Sorted Set中指定Key的Value数量
     *
     * @param key
     * @return
     */
    public Long sizeZSet(String key) {
        return myRedisDao.size(key);
    }
    /**
     * 获取list长度
     * @param key
     * @return
     */
    public Long getSize(String key){return myRedisDao.getSize(key);}
}
