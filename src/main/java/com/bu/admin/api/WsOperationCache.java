package com.bu.admin.api;

import com.bu.admin.cache.CacheRegionConstant;
import com.bu.admin.cache.CacheService;
import com.bu.admin.utils.sequence.SequenceGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class WsOperationCache {


    @Value("${spring.application.name}")
    private String applicationName;


    public long getSerialNumber() {
        return SequenceGenerator.nextValue(applicationName);
    }


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
