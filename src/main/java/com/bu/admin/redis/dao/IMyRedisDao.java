package com.bu.admin.redis.dao;

import com.bu.admin.cache.MyRedisPipeline;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public interface IMyRedisDao {


    public long del(String... keys);
    

    public void set(byte[] key, byte[] value, long liveTime);


    public void set(String key, String value, long liveTime);


    public void set(String key, String value);


    public void set(byte[] key, byte[] value);


    public String get(String key);


    public boolean exists(String key);


    public String flushDB();
    

    public Long push(String key, String value);


    Long pushRight(String key, String value);

    public String pop(String key);


    public String bpop(String key, long timeout, TimeUnit unit);


    public Long in(String key, String value);
    

    public String out(String key);


    public String bout(String key, long timeout, TimeUnit unit);
    

    public Long length(String key);
    

    public List<String> range(String key, long start, long end);
    

    public void remove(String key, long i, String value);
    

    public String index(String key, long index);
    

    public void set(String key, long index, String value);
    

    public void trim(String key, long start, long end);
    

    public Boolean add(String key, String value, double score);

    public Long add(String key, Set<ZSetOperations.TypedTuple<String>> tuples);
    

    public Set<String> rangeSet(String key, long start, long end);
    

    public Long remove(String key, Object o);
    

    public Long removeRange(String key, long start, long end);
    

    public Long size(String key);

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

    public Boolean expireTime(String key, long time, TimeUnit unit);

    public Boolean acquireLock(final String lockName, final long expire);


    public void releaseLock(String lockName);

    public String getType(String key);


    public Long increment
    (String key, long delta);

    public  Long getSize(String key);

    List<Object> executePipeline(MyRedisPipeline pipeline);
}
