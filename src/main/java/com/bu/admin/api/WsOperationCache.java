package com.bu.admin.api;

import com.bu.admin.cache.CacheRegionConstant;
import com.bu.admin.cache.CacheService;
import com.bu.admin.utils.sequence.SequenceGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by ghostWu on 2017/5/4.
 */
@Service
public class WsOperationCache {


    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * 获取调用流水号
     *
     * @return
     */
    public long getSerialNumber() {
        return SequenceGenerator.nextValue(applicationName);
    }

    /**
     * 用户token是否存在
     *
     * @param token
     * @return
     */
    public boolean userTokenExists(String token) {
        return CacheService.INST.exists(CacheRegionConstant.USER_TOKENS.name(), token);
    }

    public void resetTokenExpireTime(String token, long expire) {
        CacheService.INST.expireTime(CacheRegionConstant.USER_TOKENS.name(), token, expire);
        CacheService.INST.expireTime(CacheRegionConstant.USER_EXTEND_INFO.name(), token, expire);
    }

    public boolean checkPermission() {
        return true;
    }

}
