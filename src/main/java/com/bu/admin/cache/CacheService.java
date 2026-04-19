package com.bu.admin.cache;

import com.bu.admin.utils.JSONUtils;
import com.bu.admin.cache.exception.CacheException;
import com.bu.admin.extend.exception.ErrorCodes;
import com.bu.admin.redis.dao.IMyRedisDao;
import com.bu.admin.utils.ServiceLocator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 缓存服务
 */
public enum CacheService {

    INST; // 单例

    private static final int THRESHOLD_VALUE = 300;
    private static final Logger logger = LoggerFactory.getLogger(CacheService.class);
    private static final String CACHE ="CACHE:";

    private RedisTemplate<String, String> redisTemplate;
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 缓存初始化
     */
    CacheService() {
        this.myRedisDao = ServiceLocator.getBean(IMyRedisDao.class);
        this.redisTemplate = ServiceLocator.getBean(RedisTemplate.class, "redisTemplate");
        this.stringRedisTemplate = ServiceLocator.getBean(StringRedisTemplate.class, "stringRedisTemplate");
    }

    public <T> T execute(String region, String key, RedisCommand<T> command) {
        String redisKey = this.getRedisKey(region, key);
        return command.execute(redisKey, redisTemplate, stringRedisTemplate);
    }


    public <T> T execute(String regionKey, RedisCommand<T> command) {
        String redisKey = this.getRedisKey(regionKey);
        return command.execute(redisKey, redisTemplate, stringRedisTemplate);
    }


    /**
     * 执行redis dao层方法
     * @param region
     * @param key
     * @param command
     * @return
     */
    public <T> T execute(String region, String key, MyRedisCommand<T> command) {
        String redisKey = this.getRedisKey(region, key);
        return command.execute(redisKey, myRedisDao);
    }

    /**
     * 执行redis dao层方法
     * @param regionKey
     * @param command
     * @return
     */
    public <T> T execute(String regionKey, MyRedisCommand<T> command) {
        String redisKey = this.getRedisKey(regionKey);
        return command.execute(redisKey, myRedisDao);
    }


    /**
     * 执行单资源锁获取
     * @param region
     * @param key
     * @param expire
     */
    public boolean acquireSingleLock(String region, String key, long expire) {
        return myRedisDao.acquireLock(getRedisKey(region, key),   (int) expire);
    }


    /**
     * 执行redis pipeline
     * @param pipeline
     * @return
     */
    public List<Object> executePipeline(MyRedisPipeline pipeline) {
        return myRedisDao.executePipeline(pipeline);
    }


    /**
     * 获取缓存数据，不支持集合和map
     * @param region
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getObject(String region, String key, Class<T> clazz) {
        if (clazz == null) {
            throw new CacheException(ErrorCodes.CACHE.EMPTY_CLASS_OBJECT);
        }

        if (Collection.class.isAssignableFrom(clazz) || Map.class.isAssignableFrom(clazz)) {
            throw new CacheException(ErrorCodes.CACHE.GET_UNSUPPORTED_COLLECTION_OR_MAP, "获取缓存数据不支持的类型:" + clazz.getName());
        }

        String redisKey = getRedisKey(region, key);

        String value = myRedisDao.get(redisKey);
        if (clazz == String.class) {
            return (T) value;
        }

        return JSONUtils.getJson(value, clazz);
    }

    public void put(String region, String key, Object value, long expire) {

        Class<?> clazz = value.getClass();
        if (Collection.class.isAssignableFrom(clazz) || Map.class.isAssignableFrom(clazz)) {
            throw new CacheException(ErrorCodes.CACHE.GET_UNSUPPORTED_COLLECTION_OR_MAP, "获取缓存数据不支持的类型:" + clazz.getName());
        }

        String redisKey = getRedisKey(region, key);
        myRedisDao.set(redisKey, getStringValue(value), expire);
    }

    public void put(String region, String key, Set<?> value, long expire) {
        String redisKey = getRedisKey(region, key);

        if (value.isEmpty()) {
            return;
        }

        double score = 0;
        Set<ZSetOperations.TypedTuple<String>> set = new HashSet<>();
        for (Object v : value) {
            set.add(new DefaultTypedTuple<>(getStringValue(v), score++));
            if (set.size() % THRESHOLD_VALUE == 0) {
                myRedisDao.add(redisKey, set);
                set = new HashSet<>();
            }
        }

        if (!set.isEmpty()) {
            myRedisDao.add(redisKey, set);
        }

        if (expire > 0) {
            myRedisDao.expireTime(redisKey, expire, TimeUnit.SECONDS);
        }
    }

    public void put(String region, String key, Map<?, ?> value, long expire) {
        String redisKey = getRedisKey(region, key);

        if (value.size() <=0) {
            return;
        }

        if (value.size() <= THRESHOLD_VALUE) {
            myRedisDao.hmset(key, value);
        } else {
            Map<Object, Object> map = new HashMap<>();
            for (Map.Entry<?, ?> entry : value.entrySet()) {
                map.put(entry.getKey(), entry.getValue());
                if (map.size() % THRESHOLD_VALUE == 0) {
                    myRedisDao.hmset(key, map);
                    map = new HashMap<>();
                }
            }

            if (map.size() > 0) {
                myRedisDao.hmset(key, map);
            }
        }

        if (expire > 0) {
            myRedisDao.expireTime(redisKey, expire, TimeUnit.SECONDS);
        }
    }

    public boolean exists(String region, String key) {
        String redisKey = getRedisKey(region, key);
        return myRedisDao.exists(redisKey);
    }

    public void expireTime(String region, String key, long expire) {
        String redisKey = getRedisKey(region, key);
        if (expire > 0) {
            myRedisDao.expireTime(redisKey, expire, TimeUnit.SECONDS);
        }
    }

    public void remove(String region, String key) {
        String redisKey = getRedisKey(region, key);
        myRedisDao.del(redisKey);
    }

    public void remove(String region) {
        remove0(CACHE + region + ":*");
    }

    public void remove() {
        remove0(CACHE+"*");
    }

    private void remove0(String regx) {
        Set<String> keys = myRedisDao.getKeysByRegx(regx, 1000);
        if (keys.isEmpty()) {
            return;
        }


        if (keys.size() <= THRESHOLD_VALUE) {
            myRedisDao.del(keys.stream().toArray(String[]::new));
            // 管道方式执行删除目前存在位置问题
        } else {
            List<String> list = new ArrayList<>();
            for (String key : keys) {
                list.add(key);
                if (list.size() % THRESHOLD_VALUE == 0) {
                    myRedisDao.del(list.stream().toArray(String[]::new));
                    list.clear();
                }
            }

            if (!list.isEmpty()) {
                myRedisDao.del(list.stream().toArray(String[]::new));
            }
        }
    }

    /**
     * 获取底层key
     * @param regionKey
     * @return
     */
    public String getRedisKey(String regionKey) {

        if (StringUtils.isEmpty(regionKey)) {
            throw new CacheException(ErrorCodes.CACHE.EMPTY_REGION);
        }

        return CACHE + regionKey + ":UNQ";
    }

    /**
     * 获取底层key
     * @param region
     * @param key
     * @return
     */
    public String getRedisKey(String region, String key) {

        if (StringUtils.isEmpty(region)) {
            throw new CacheException(ErrorCodes.CACHE.EMPTY_REGION);
        }

        if (StringUtils.isEmpty(key)) {
            throw new CacheException(ErrorCodes.CACHE.EMPTY_KEY);
        }

        return CACHE + region + ":" + key;
    }

    public byte[] getBytes(String str) {
        try {
            return str.getBytes(StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.error("缓存服务字符串获取字节数据报错: [{}]", str);
            return new byte[0];
        }
    }

    private String getStringValue(Object o) {
        if (o instanceof String o1) {
            return o1;
        } else if (o instanceof Number) {
            return String.valueOf(o);
        } else {
            return JSONUtils.toJson(o);
        }
    }

    private final IMyRedisDao myRedisDao;
}
