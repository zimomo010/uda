package com.bu.admin.redis.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class RedisClusterPropertyConfigurer extends PropertyPlaceholderConfigurer {

    private static final Map<String, String> propertiesCopy = new HashMap<>();

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);

        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = String.valueOf(props.get(keyStr));
            propertiesCopy.put(keyStr, value);
        }
    }

    public static String getProperty(String key) {
        return propertiesCopy.get(key);
    }

    public static String getProperty(String key, String defaultStr) {
        String val = propertiesCopy.get(key);
        if (!StringUtils.isEmpty(val)) {
            return val;
        }
        return defaultStr;
    }

    public static Integer getIntProperty(String key) {
        String val = propertiesCopy.get(key);
        if (!StringUtils.isEmpty(val)) {
            return Integer.valueOf(val);
        }
        return null;
    }

    public static Integer getIntProperty(String key, Integer defaultInt) {
        try {
            String val = propertiesCopy.get(key);
            if (!StringUtils.isEmpty(val)) {
                return Integer.valueOf(val);
            }
        } catch (Exception e) {
            //
        }
        return defaultInt;
    }

    public static boolean hasProperty(String key) {
        return propertiesCopy.containsKey(key);
    }

    public static Map<String, String> getPropMapCopy() {
        return Collections.unmodifiableMap(propertiesCopy);
    }

}
