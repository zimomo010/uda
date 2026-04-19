package com.bu.admin.redis.dao.impl;

import com.bu.admin.cache.MyRedisPipeline;
import com.bu.admin.cache.exception.CacheException;
import com.bu.admin.extend.exception.ErrorCodes;
import com.bu.admin.redis.dao.IMyRedisDao;
import com.bu.admin.redis.service.RedisClusterPropertyConfigurer;
import io.lettuce.core.RedisURI;
import io.lettuce.core.ScanArgs;
import io.lettuce.core.ScanIterator;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Repository("myRedisDao")
public class MyRedisDaoImpl implements IMyRedisDao {

    private static final Logger logger = LoggerFactory.getLogger(MyRedisDaoImpl.class);

    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static String redisCode = "utf-8";

    public long del(final String... keys) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> connection.del(Arrays.stream(keys).map(this::getBytes).toArray(byte[][]::new)));
    }



    public void set(final byte[] key, final byte[] value, final long liveTime) {
        redisTemplate.execute(new RedisCallback<Object>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return 1L;
            }
        });
    }
    /**
     * 获取锁
     * @param lockName
     * @param expired
     * @return
     */
    public Boolean acquireLock(final String lockName, final long expired) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            try {
                boolean success;

                String lock = lockName + "-LOCK";

                byte[] lockNameBytes = redisTemplate.getStringSerializer().serialize(lock);

                long value = System.currentTimeMillis() + (expired * 1000) + 1;
                byte[] valueBytes = redisTemplate.getStringSerializer().serialize(String.valueOf(value));

                Boolean acquired = connection.setNX(lockNameBytes, valueBytes);
                //SETNX成功，则成功获取一个锁
                if (Boolean.TRUE.equals(acquired))
                    success = true;
                    //SETNX失败，说明锁仍然被其他对象保持，检查其是否已经超时
                else {
                    long oldValue = Long.parseLong(redisTemplate.getStringSerializer().deserialize(connection.get(lockNameBytes)));

                    //超时
                    if (oldValue < System.currentTimeMillis()) {
                        String getValue = redisTemplate.getStringSerializer().deserialize(connection.getSet(lockNameBytes, valueBytes));
                        // 获取锁成功
                        if (Long.parseLong(getValue) == oldValue) {
                            success = true;
                        } else {
                            // 已被其他进程捷足先登了
                            success = false;
                        }
                    } else {
                        //未超时，则直接返回失败
                        success = false;
                    }

                }

                return success;
            }catch (Exception e) {
                return false;
            }
        });
    }

    /**
     * 释放锁
     * @param lockName
     */
    public void releaseLock(final String lockName) {
        redisTemplate.execute((RedisCallback<Void>) connection -> {
            String lock = lockName + "-LOCK";

            byte[] lockNameBytes = redisTemplate.getStringSerializer().serialize(lock);

            // 避免删除非自己获取得到的锁
            connection.del(lockNameBytes);

            return null;
        });
    }

    public void set(String key, String value, long liveTime) {
        try {
            this.set(key.getBytes(redisCode), value.getBytes(redisCode), liveTime);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void set(String key, String value) {
        this.set(key, value, 0L);
    }

    public void set(byte[] key, byte[] value) {
        this.set(key, value, 0L);
    }

    public String get(final String key) {
        return redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    return new String(connection.get(key.getBytes(redisCode)), redisCode);
                } catch (NullPointerException e) {
                    // ignore
                } catch (Exception e) {
                    logger.error("获取key值报错", e);
                }
                return "";
            }
        });
    }

    public boolean exists(final String key) {
        return (Boolean) redisTemplate.execute((RedisCallback<Object>) connection -> {
            try {
                return connection.exists(key.getBytes(redisCode));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    public String flushDB() {
        return redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }

    public Long push(String key, String value) {
        return stringRedisTemplate.opsForList().leftPush(key, value);
    }

    public Long pushRight(String key, String value) {
        return stringRedisTemplate.opsForList().rightPush(key, value);
    }

    public String pop(String key) {
        return stringRedisTemplate.opsForList().leftPop(key);
    }

    public String bpop(String key, long timeout, TimeUnit unit) {
        return stringRedisTemplate.opsForList().leftPop(key, timeout, unit);
    }

    public Long in(String key, String value) {
        return stringRedisTemplate.opsForList().rightPush(key, value);
    }

    public String out(String key) {
        return stringRedisTemplate.opsForList().rightPop(key);
    }

    public String bout(String key, long timeout, TimeUnit unit) {
        return stringRedisTemplate.opsForList().rightPop(key, timeout, unit);
    }

    public Long length(String key) {
        return stringRedisTemplate.opsForList().size(key);
    }

    public List<String> range(String key, long start, long end) {
        return stringRedisTemplate.opsForList().range(key, start, end);
    }

    public void remove(String key, long i, String value) {
        stringRedisTemplate.opsForList().remove(key, i, value);
    }

    public String index(String key, long index) {
        return stringRedisTemplate.opsForList().index(key, index);
    }

    public void set(String key, long index, String value) {
        stringRedisTemplate.opsForList().set(key, index, value);
    }

    public void trim(String key, long start, long end) {
        stringRedisTemplate.opsForList().trim(key, start, end);
    }

    public Boolean add(String key, String value, double score) {
        return stringRedisTemplate.opsForZSet().add(key, value, score);
    }

    public Long add(String key, Set<ZSetOperations.TypedTuple<String>> tuples) {
        return stringRedisTemplate.opsForZSet().add(key, tuples);
    }

    public Set<String> rangeSet(String key, long start, long end) {
        return stringRedisTemplate.opsForZSet().range(key, start, end);
    }

    public Long remove(String key, Object o) {
        return stringRedisTemplate.opsForZSet().remove(key, o);
    }

    public Long removeRange(String key, long start, long end) {
        return stringRedisTemplate.opsForZSet().removeRange(key, start, end);
    }

    public Long size(String key) {
        return stringRedisTemplate.opsForZSet().size(key);
    }

    public Set<String> getKeysByRegx(final String pattern, final int count) {

        RedisClusterClient clusterClient = getNativeClient();
        StatefulRedisClusterConnection<String, String> connection = clusterClient.connect();
        try {
            RedisAdvancedClusterCommands<String, String> syncCommands = connection.sync();
            ScanIterator<String> scan = ScanIterator.scan(syncCommands, ScanArgs.Builder.limit(count).match(pattern));
            return scan.stream().collect(Collectors.toSet());
        } finally {
            connection.close();
            clusterClient.shutdown();
        }
    }

    private RedisClusterClient getNativeClient() {
        Set<RedisURI> nodes = getNodeIds().stream().map(prefix -> {
            RedisURI.Builder builder = RedisURI.Builder.redis(RedisClusterPropertyConfigurer.getProperty(prefix + ".host"),
                    RedisClusterPropertyConfigurer.getIntProperty(prefix + ".port"));
            if (RedisClusterPropertyConfigurer.hasProperty("redis.common.nodes.password")) {
                builder.withPassword(RedisClusterPropertyConfigurer.getProperty("redis.common.nodes.password"));
            }
            if (RedisClusterPropertyConfigurer.hasProperty( prefix + ".database")) {
                builder.withDatabase(RedisClusterPropertyConfigurer.getIntProperty(prefix + ".database"));
            }

            return builder.build();
        }).collect(Collectors.toSet());

        return RedisClusterClient.create(nodes);
    }

    private Set<String> getNodeIds() {
        return RedisClusterPropertyConfigurer.getPropMapCopy().keySet().stream().filter(str -> str.startsWith("redis.node")).map(str -> str.substring(0, 11)).collect(Collectors.toSet());
    }

    public void hmset(String key, Map<?, ?> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    public Collection<Object> hmget(String key, Collection<String> fields) {
        return redisTemplate.<String, Object>opsForHash().multiGet(key, fields);
    }

    public Set<String> hkeys(String key) {
        return redisTemplate.<String, Object>opsForHash().keys(key);
    }

    public Long hlen(String key) {
        return redisTemplate.<String, Object>opsForHash().size(key);
    }

    public Long hincrBy(String key, String field, Long value) {
        return redisTemplate.<String, Object>opsForHash().increment(key, field, value);
    }

    public Map<String, Object> hgetAll(String key) {
        return redisTemplate.<String, Object>opsForHash().entries(key);
    }

    public Boolean hexists(String key, String field) {
        return redisTemplate.<String, Object>opsForHash().hasKey(key, field);
    }

    public Object hget(String key, String field) {
        return redisTemplate.<String, Object>opsForHash().get(key, field);
    }

    public void hdel(String key, String field) {
        redisTemplate.<String, Object>opsForHash().delete(key, field);
    }

    public void hset(String key, String field, Object value) {
        redisTemplate.<String, Object>opsForHash().put(key, field, value);
    }

    public boolean hsetnx(String key, String field, Object value) {
        return redisTemplate.<String, Object>opsForHash().putIfAbsent(key, field, value);
    }

    public Boolean expireTime(String key, long time, TimeUnit unit) {
        return redisTemplate.expire(key, time, unit);
    }

    public String getType(final String key) {
        return redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                DataType dataType = connection.type(Objects.requireNonNull(redisTemplate.getStringSerializer().serialize(key)));
                return dataType.code();
            }
        });
    }

    public Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public Long getSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    @Override
    public List<Object> executePipeline(MyRedisPipeline pipeline) {
        return stringRedisTemplate.executePipelined((RedisConnection connection) -> {
            pipeline.execute(connection);
            return null;
        }, stringRedisTemplate.getKeySerializer());
    }

    private byte[] getBytes(String str) {
        try {
            return str.getBytes(redisCode);
        } catch (UnsupportedEncodingException e) {
            throw new CacheException(ErrorCodes.CACHE.GET_STRING_BYTES_ERROR);
        }
    }

}
