package com.bu.admin.system;

import com.bu.admin.api.WsOperationScanner;
import com.bu.admin.cache.CacheService;
import com.bu.admin.feign.bo.system.Operation;
import com.bu.admin.feign.bo.system.SystemOperationList;
import com.bu.admin.feign.service.UserCenterClient;
import com.bu.admin.feign.utils.ServerTokenUtils;
import com.bu.admin.utils.JSONUtils;
import com.google.gson.reflect.TypeToken;
import jakarta.annotation.Resource;
import lombok.Data;
import org.apache.commons.collections4.list.GrowthList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: ghostWu
 * @Date: 2020/3/16
 */
@Service
public class SystemInitService {

    private static final Logger logger = LoggerFactory.getLogger(SystemInitService.class);

    private static final String INIT_KEY = "init_key";
    private static final String REDIS_REGION = "SYSTEM_INIT_FLAG";
    private static final String APP_VERSION = "APP_VERSION";
    @Value("${application.version}")
    private String appVersion;
    @Value("${spring.application.name}")
    private String appName;

    @Data
    private static class Application {

        private String appVersion;
        private String appName;

    }

    @Autowired
    private WsOperationScanner wsOperationScanner;

    @Resource
    private UserCenterClient userCenterClient;

    @Init
    public void saveOperationRelation() {
        CacheService cacheService = CacheService.INST;
        try {
            logger.info("system init ...");
            if (cacheService.exists(REDIS_REGION, INIT_KEY + appName)) {
                logger.error("system ...");
                return;
            }
            cacheService.put(REDIS_REGION, INIT_KEY + appName, 1, 5 * 60L);

            setApplicationInfo();

            // 一系列初始化调用
            List<Operation> operations = wsOperationScanner.scan();
            logger.info("get {} api....", operations.size());

            if (!operations.isEmpty()) {
                SystemOperationList systemOperationList = new SystemOperationList();
                systemOperationList.setCode(appName);
                systemOperationList.setOperationList(operations);
                String logoutResponse = userCenterClient.sendSystemOperations(systemOperationList, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
                logger.info("update operation list，us response is {}", logoutResponse);
            }
        } catch (Exception e) {
            logger.error("system init error", e);
        } finally {
            cacheService.remove(REDIS_REGION, INIT_KEY + appName);
        }

        logger.info("system init finish...");

    }

    public String getApplicationVersion() {
        CacheService cacheService = CacheService.INST;
        if (cacheService.exists(REDIS_REGION, APP_VERSION)) {
            return cacheService.getObject(REDIS_REGION, APP_VERSION, String.class);
        } else {
            return "";
        }
    }

    private void setApplicationInfo() {
        CacheService cacheService = CacheService.INST;
        List<Application> applicationList;
        if (cacheService.exists(REDIS_REGION, APP_VERSION)) {
            String appVersionStr = cacheService.getObject(REDIS_REGION, APP_VERSION, String.class);
            logger.info("old application info is [{}]...", appVersionStr);
            Type type = new TypeToken<List<Application>>() {
            }.getType();
            applicationList = JSONUtils.getJson(appVersionStr, type);
            Map<String, Application> applicationMap = applicationList.stream().collect(Collectors.toMap(Application::getAppName, v -> v));
            if (null == applicationMap.get(appName)) {
                addApplicationVersion(applicationList);
            } else {
                applicationMap.get(appName).setAppVersion(appVersion);
                applicationList = new ArrayList<>(applicationMap.values());
            }
        } else {
            applicationList = new GrowthList<>();
            addApplicationVersion(applicationList);
        }
        logger.info("new application info is [{}]...", applicationList);
        cacheService.put(REDIS_REGION, APP_VERSION, JSONUtils.toJSONString(applicationList), 10 * 365 * 24 * 60 * 60L);
    }

    private void addApplicationVersion(List<Application> applicationList) {
        Application application = new Application();
        application.setAppName(appName);
        application.setAppVersion(appVersion);
        applicationList.add(application);
    }


}
