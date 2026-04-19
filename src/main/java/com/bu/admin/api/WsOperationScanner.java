package com.bu.admin.api;

import com.bu.admin.feign.bo.system.Operation;
import com.bu.admin.utils.AopTargetUtils;
import com.bu.admin.utils.ServiceLocator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName WsOperationScanner
 * @Description 操作权限
 * @Author ghostWu
 * @Date 2019/7/11
 */
@Service
public class WsOperationScanner {

    private static final Logger logger = LoggerFactory.getLogger(WsOperationScanner.class);

    @Value("${spring.application.name}")
    private String applicationName;

    public List<Operation> scan() {
        ApplicationContext applicationContext = ServiceLocator.getApplicationContext();
        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(WsOperation.class);
        List<Operation> list = new ArrayList<>();
        for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
            String beanName = entry.getKey();
            Object obj = entry.getValue();
            try {
                Class<?> clazz = AopTargetUtils.getTarget(obj).getClass();
                WsOperation parentOperation = null;
                if (clazz.isAnnotationPresent(WsOperation.class)) {
                    parentOperation = clazz.getAnnotation(WsOperation.class);
                }
                if (null != parentOperation) {
                    String parentOperationName = parentOperation.value();
                    RequestMapping requestMapping = null;
                    if (clazz.isAnnotationPresent(RequestMapping.class)) {
                        requestMapping = clazz.getAnnotation(RequestMapping.class);
                    }
                    if (null != requestMapping) {
                        adOperation(parentOperationName, requestMapping.value()[0], clazz, list);
                    }
                }


            } catch (Exception e) {
                logger.error("解析Bean[" + beanName + "]Class[" + obj.getClass().getName() + "]失败", e);
            }
        }

        return list;
    }

    private void adOperation(String parentOperationName, String requestName, Class<?> clazz, List<Operation> list) {
        String parentOperationCode = "/" + applicationName + requestName;
        if (!parentOperationCode.contains("/front/") && !parentOperationCode.contains("/server/")) {
            Operation parentOperationJson = new Operation();
            parentOperationJson.setCode(parentOperationCode);
            parentOperationJson.setName(parentOperationName);
            parentOperationJson.setParentCode("-1");
            parentOperationJson.setParent(true);

            List<Operation> subOperations = new ArrayList<>();
            Method[] methods = clazz.getMethods();

            dealMethods(methods, parentOperationCode, subOperations);
            if (!subOperations.isEmpty()) {
                list.add(parentOperationJson);
                list.addAll(subOperations);
            }
        }
    }

    private void dealMethods(Method[] methods, String parentOperationCode, List<Operation> subOperations) {
        for (Method method : methods) {
            if (method.isAnnotationPresent(WsOperation.class)) {
                WsOperation subOperation = method.getAnnotation(WsOperation.class);
                String subOperationName = subOperation.value();
                String subOperationCode = "";
                if (method.isAnnotationPresent(GetMapping.class)) {
                    subOperationCode = parentOperationCode + method.getAnnotation(GetMapping.class).value()[0];
                } else if (method.isAnnotationPresent(PostMapping.class)) {
                    subOperationCode = parentOperationCode + method.getAnnotation(PostMapping.class).value()[0];
                }

                if (StringUtils.isEmpty(subOperationCode)) {
                    continue;
                }

                Operation subOperationJson = new Operation();
                subOperationJson.setCode(subOperationCode);
                subOperationJson.setName(subOperationName);
                subOperationJson.setParentCode(parentOperationCode);
                subOperationJson.setParent(false);
                subOperations.add(subOperationJson);
            }
        }
    }
}
