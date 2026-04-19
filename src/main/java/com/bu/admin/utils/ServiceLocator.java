package com.bu.admin.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * 获取Spring应用的bean对象
 * @author ghostWu
 */
@Service
@SuppressWarnings("squid:S2696")
public class ServiceLocator implements ApplicationContextAware {

    // Spring应用上下文环境  
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     *
     */
    @Override
    public  void  setApplicationContext(ApplicationContext act) {
        ServiceLocator.applicationContext = act;
    }


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取对象 
     * 这里重写了bean方法，起主要作用 
     * @param name 
     * @return Object 一个以所给名字注册的bean的实例 
     * @throws org.springframework.beans.BeansException
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    /**
     * 获取对象
     * 这里重写了bean方法，起主要作用
     * @param name
     * @return Object 一个以所给名字注册的bean的实例
     * @throws org.springframework.beans.BeansException
     */  
    public static <T> T getBean(Class<T> clazz, String name) throws BeansException {
        try {
            return applicationContext.getBean(name, clazz);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取对象
     * @param clazz
     * @param <T>
     * @return
     * @throws BeansException
     */
    public static <T> T getBean(Class<T> clazz) throws BeansException {
        return applicationContext.getBean(clazz);
    }

    public static String getActiveProfile() {
        return applicationContext.getEnvironment().getActiveProfiles()[0];
    }
}
