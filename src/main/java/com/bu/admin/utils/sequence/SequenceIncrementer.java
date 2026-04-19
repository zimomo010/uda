package com.bu.admin.utils.sequence;

import com.bu.admin.cache.CacheRegionConstant;
import com.bu.admin.cache.CacheService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service("sequenceIncrementer")
public class SequenceIncrementer {

    private final Map<String, SerialNumberCache> serialNumberCacheMap = new HashMap<>();


    long nextValue(String seqName) {
        SerialNumberCache serialNumberCache = serialNumberCacheMap.get(seqName);
        if (serialNumberCache == null) {
            synchronized (this) {
                serialNumberCache = Optional.ofNullable(serialNumberCacheMap.get(seqName)).orElse(new SerialNumberCache());

                serialNumberCacheMap.put(seqName, serialNumberCache);
            }
        }

        synchronized (serialNumberCache) {
            if (serialNumberCache.hasNext()) {
                return serialNumberCache.next();
            }

            int step = 1000;
            long end = CacheService.INST.execute(CacheRegionConstant.SEQUENCE_NO.name(),
                    seqName, (key, dao) -> dao.increment(key, step));
            long start = end - step + 1;

            serialNumberCache.reset(start, end);

            return serialNumberCache.next();
        }
    }

    // 序列发生器
    private static class SerialNumberCache {
        long current;
        long end;

        void reset(long start, long end) {
            this.current = start - 1;
            this.end = end;
        }

        boolean hasNext() {
            return current < end;
        }

        long next() {
            return ++current;
        }
    }
}
