package com.bu.admin.redis.dao;

import com.bu.admin.cache.MyRedisPipeline;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis key-value存取接口
 * @author ghostWu
 */
public interface IMyRedisDao {

    /**
     * 通过key删除
     * @param keys
     */
    public long del(String... keys);
    
    /**
     * 添加key value 并且设置存活时间(byte)
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(byte[] key, byte[] value, long liveTime);

    /**
     * 添加key value 并且设置存活时间
     * @param key
     * @param value
     * @param liveTime 单位秒
     */
    public void set(String key, String value, long liveTime);

    /**
     * 添加key value
     * @param key
     * @param value
     */
    public void set(String key, String value);

    /**
     * 添加key value (字节)(序列化)
     * @param key
     * @param value
     */
    public void set(byte[] key, byte[] value);

    /**
     * 获取redis value (String)
     * @param key
     * @return
     */
    public String get(String key);

    /**
     * 检查key是否已经存在
     * @param key
     * @return
     */
    public boolean exists(String key);

    /**
     * 清空redis 所有数据
     * @return
     */
    public String flushDB();
    
    /** 
     * 压栈 List
     * @param key 
     * @param value 
     * @return 
     */  
    public Long push(String key, String value);

    /***
     * 压栈 list rightPush
     * @param key
     * @param value
     * @return
     */
    Long pushRight(String key, String value);
    /** 
     * 出栈   List
     * @param key 
     * @return 
     */  
    public String pop(String key);

    /**
     * 出栈
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    public String bpop(String key, long timeout, TimeUnit unit);

    /** 
     * 入队 List
     * @param key 
     * @param value 
     * @return 
     */  
    public Long in(String key, String value);
    
    /** 
     * 出队 List
     * @param key 
     * @return 
     */  
    public String out(String key);

    /**
     * 出队
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    public String bout(String key, long timeout, TimeUnit unit);
    
    /** 
     * 栈/队列长 Lis
     * @param key 
     * @return 
     */  
    public Long length(String key);
    
    /** 
     * 范围检索 List
     * @param key 
     * @param start 
     * @param end 
     * @return 
     */  
    public List<String> range(String key, long start, long end);
    
    /** 
     * 移除 List
     * @param key 
     * @param i 
     * @param value 
     */  
    public void remove(String key, long i, String value);
    
    /** 
     * 检索 List
     * @param key 
     * @param index 
     * @return 
     */  
    public String index(String key, long index);
    
    /** 
     * 置值 List
     * @param key 
     * @param index 
     * @param value 
     */  
    public void set(String key, long index, String value);
    
    /** 
     * 裁剪 List
     * @param key 
     * @param start 
     * @param end 
     */  
    public void trim(String key, long start, long end);
    
    /**
     * 添加 Value To Sorted set
     * @param key
     * @param value
     * @param score
     * @return
     */
    public Boolean add(String key, String value, double score);

    public Long add(String key, Set<ZSetOperations.TypedTuple<String>> tuples);
    
    /**
     * 根据Key从Sorted Set中查找指定范围的Value
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> rangeSet(String key, long start, long end);
    
    /**
     * 删除Sorted Set中指定Key的所有Value
     * @param key
     * @param o
     * @return
     */
    public Long remove(String key, Object o);
    
    /**
     * 根据Key，删除Sorted Set中指定范围的Value
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Long removeRange(String key, long start, long end);
    
    /**
     * 查询Sorted Set中指定Key的Value数量
     * @param key
     * @return
     */
    public Long size(String key);
    
    /**
     * 耗用资源，谨慎使用
     * @param pattern
     * @param count
     * @return
     */
    public Set<String> getKeysByRegx(String pattern, int count);

    public void hmset(String key, Map<?, ?> map);

    public Collection<Object> hmget(String key, Collection<String> fields);

    public Set<String> hkeys(String key);

    public Long hlen(String key);

    public Long hincrBy(String key, String field, Long value);

    public Map<String, Object> hgetAll(String key);

    public Boolean hexists(String key, String field);

    public Object hget(String key, String field);

    public void hdel(String key, String field);

    public void hset(String key, String field, Object value);

    public boolean hsetnx(String key, String field, Object value);

    /**
     * key过期时间设置
     * @param key
     * @param time
     * @param unit
     * @return
     */
    public Boolean expireTime(String key, long time, TimeUnit unit);

    /**
     * 获取锁
     * @param lockName
     * @param expire
     * @return
     */
    public Boolean acquireLock(final String lockName, final long expire);

    /**
     * 释放锁
     * @param lockName
     */
    public void releaseLock(String lockName);

    /**
     * 获取key对应的值类型
     * @param key
     * @return
     */
    public String getType(String key);

    /**
     * 对key进行自增/自减1
     * @param key
     * @param delta 1：自增；-1：自减
     * @return
     */
    public Long increment
    (String key, long delta);

    /**
     * 获取list长度
     * @param key
     * @return
     */
    public  Long getSize(String key);

    /**
     * 执行redis pipeline
     * @param pipeline
     * @return
     */
    List<Object> executePipeline(MyRedisPipeline pipeline);
}
