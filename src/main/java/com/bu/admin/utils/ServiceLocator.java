package com.bu.admin.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;


@Service
@SuppressWarnings("squid:S2696")
public class ServiceLocator implements ApplicationContextAware {

    private static ApplicationContext applicationContext;


    @Override
    public  void  setApplicationContext(ApplicationContext act) {
        ServiceLocator.applicationContext = act;
    }


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }


    public static <T> T getBean(Class<T> clazz, String name) throws BeansException {
        try {
            return applicationContext.getBean(name, clazz);
        } catch (Exception e) {
            return null;
        }
    }


    public static <T> T getBean(Class<T> clazz) throws BeansException {
        return applicationContext.getBean(clazz);
    }

    public static String getActiveProfile() {
        return applicationContext.getEnvironment().getActiveProfiles()[0];
    }
}
