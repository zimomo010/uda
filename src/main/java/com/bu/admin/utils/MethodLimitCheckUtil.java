package com.bu.admin.utils;


import com.bu.admin.business.cache.BusCacheRegionConstant;
import com.bu.admin.cache.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


public class MethodLimitCheckUtil {

    private MethodLimitCheckUtil() {
        throw new IllegalStateException("Utility class");
    }

    protected static final Logger logger = LoggerFactory.getLogger(MethodLimitCheckUtil.class);

    public static final int TOTAL = 0;
    public static final int MINUTE = 1;
    public static final int HOUR = 2;
    public static final int DAY = 3;
    public static final int MONTH = 4;

    public static boolean methodLimitCheck(String methodName, String busKey, Integer limitNum, int limitType) {
        String busKeyStr = "";
        Date now = new Date();
        String timeKey = DateConverterUtils.formatDateToString(now, DateConverterUtils.FormatterType.FORMAT4);
        long eTime = 1;
        switch (limitType) {
            case TOTAL:
                busKeyStr = methodName;
                eTime = 30 * 24 * 60 * 60L;
                break;
            case MINUTE:
                busKeyStr = methodName + timeKey.substring(0, 12) + busKey;
                eTime = 60L;
                break;
            case HOUR:
                busKeyStr = methodName + timeKey.substring(0, 10) + busKey;
                eTime = 60 * 60L;
                break;
            case DAY:
                busKeyStr = methodName + timeKey.substring(0, 8) + busKey;
                eTime = 24 * 60 * 60L;
                break;
            case MONTH:
                busKeyStr = methodName + timeKey.substring(0, 6) + busKey;
                eTime = 30 * 24 * 60 * 60L;
                break;
            default:
                break;
        }
        return checkLimit(limitNum, busKeyStr, eTime);
    }

    private static Boolean checkLimit(Integer limitNum, String busKeyStr, long eTime) {
        Boolean result = true;
        if (!CacheService.INST.exists(BusCacheRegionConstant.METHOD_REQUEST_LIMIT.name(), busKeyStr)) {
            CacheService.INST.put(BusCacheRegionConstant.METHOD_REQUEST_LIMIT.name(), busKeyStr, 1L, eTime);
        } else {
            long requestNums = CacheService.INST.getObject(BusCacheRegionConstant.METHOD_REQUEST_LIMIT.name(), busKeyStr, long.class);
            if (requestNums >= limitNum) {
                logger.info("out of limit ,bus key is [{}],limit is [{}]", busKeyStr, limitNum);
                result = false;
            } else {
                requestNums += 1;
                CacheService.INST.put(BusCacheRegionConstant.METHOD_REQUEST_LIMIT.name(), busKeyStr, requestNums, eTime);
            }
        }
        return result;
    }

}
