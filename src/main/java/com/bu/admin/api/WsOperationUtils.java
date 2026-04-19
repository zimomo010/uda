package com.bu.admin.api;

import com.bu.admin.utils.JSONUtils;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.annotation.Annotation;
import java.util.Map;


public class WsOperationUtils {


    private WsOperationUtils() {
        throw new IllegalStateException("Utility class");
    }


    public static void populateParams(MethodSignature methodSignature, Object[] args) {
        Annotation[][] parameterAnnotations = methodSignature.getMethod().getParameterAnnotations();
        String[] parameterNames = methodSignature.getParameterNames();
        JsonObject extraParams = new JsonObject(); // 额外的方法入参

        if (methodSignature.getMethod().isAnnotationPresent(GetMapping.class)) {
            //get
            setGetParams(args, parameterAnnotations, parameterNames, extraParams);
        } else if (methodSignature.getMethod().isAnnotationPresent(PostMapping.class)) {
            // post请求
            setPostParams(args, parameterAnnotations, parameterNames, extraParams);
        }
        JSONUtils.addParams(WsContext.getContext().getRequest().getAsJsonObject(), extraParams);
    }

    private static void setHeaders(JsonObject extraParams, Object arg, Annotation[] parameterAnnotation, String parameterName) {
        if (parameterAnnotation.length > 0 && parameterAnnotation[0] instanceof RequestHeader pv) {
            String key;
            if (StringUtils.isNotEmpty(pv.value())) {
                key = pv.value();
            } else if (StringUtils.isNotEmpty(pv.name())) {
                key = pv.name();
            } else {
                key = parameterName;
            }
            if ("Authorization".equalsIgnoreCase(key) || "token".equalsIgnoreCase(key)) {
                WsContext.getContext().setToken(String.valueOf(arg));
            }
            if ("MiServerName".equalsIgnoreCase(key)) {
                WsContext.getContext().setServerName(String.valueOf(arg));
            }
            extraParams.add(key, JSONUtils.toJSONElement(arg));
        }
    }

    /**
     * check input params null
     *
     */
    public static void checkInputEmpty(WsOperation processAnnotation) {
        String[] requiredParams = processAnnotation.requiredParams();
        if (requiredParams != null && requiredParams.length > 0) {
            RequestStrVerifyUtils.jsonObjectEmptyVerify(WsContext.getContext().getRequest().getAsJsonObject(), requiredParams);
        }
    }


    private static void setGetParams(Object[] args, Annotation[][] parameterAnnotations, String[] parameterNames, JsonObject extraParams) {
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (parameterAnnotations[i].length > 0 && parameterAnnotations[i][0] instanceof PathVariable pathVariable) {
                String key = getKeyByPathVariable(pathVariable, parameterNames[i]);
                extraParams.add(key, JSONUtils.toJSONElement(arg));
            }
            if (parameterAnnotations[i].length > 0 && parameterAnnotations[i][0] instanceof RequestParam) {
                dealGetParamsMethod(arg, parameterAnnotations[i][0], parameterNames[i], extraParams);
            }

            if (parameterAnnotations[i].length > 0 && parameterAnnotations[i][0] instanceof ModelAttribute) {
                JSONUtils.addParams(extraParams, arg);
            }

            setHeaders(extraParams, arg, parameterAnnotations[i], parameterNames[i]);
        }
    }

    private static void setPostParams(Object[] args, Annotation[][] parameterAnnotations, String[] parameterNames, JsonObject extraParams) {
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (null != arg) {
                setParams(arg, parameterAnnotations, parameterNames, extraParams, i);
                setHeaders(extraParams, arg, parameterAnnotations[i], parameterNames[i]);
            }
        }
    }

    private static void setParams(Object arg, Annotation[][] parameterAnnotations,
                                  String[] parameterNames, JsonObject extraParams, int i) {
        Class<?> clazz = arg.getClass();

        if (parameterAnnotations[i].length > 0 && parameterAnnotations[i][0] instanceof RequestBody) {
            JSONUtils.addParams(extraParams, arg);
        }
        if (parameterAnnotations[i].length > 0
                && parameterAnnotations[i][0] instanceof RequestParam requestParam
                && arg instanceof MultipartFile) {
            String key = getKeyByRequestParam(requestParam, parameterNames[i]);
            extraParams.add(key, JSONUtils.toJSONElement(arg));

            extraParams.addProperty(key, "type is file...");
        }
        if (parameterAnnotations[i].length == 0 && (arg instanceof String || ClassUtils.isPrimitiveOrWrapper(clazz))) {
            extraParams.add(parameterNames[i], JSONUtils.toJSONElement(arg));
        }
    }

    private static void dealGetParamsMethod(Object arg, Annotation parameterAnnotation, String parameterName, JsonObject extraParams) {
        if (arg instanceof Map) {
            for (Map.Entry<String, Object> entry : ((Map<String, Object>) arg).entrySet()) {
                extraParams.add(entry.getKey(), JSONUtils.toJSONElement(entry.getValue()));
            }
        } else {
            String key = getKeyByRequestParam((RequestParam) parameterAnnotation, parameterName);
            extraParams.add(key, JSONUtils.toJSONElement(arg));
        }
    }

    private static String getKeyByPathVariable(PathVariable pv, String value) {
        String key;
        if (StringUtils.isNotEmpty(pv.value())) {
            key = pv.value();
        } else if (StringUtils.isNotEmpty(pv.name())) {
            key = pv.name();
        } else {
            key = value;
        }
        return key;

    }

    private static String getKeyByRequestParam(RequestParam pv, String value) {
        String key = StringUtils.isNotEmpty(pv.value()) ? pv.value() : pv.name();
        if (StringUtils.isNotEmpty(key)) {
            key = value;
        }
        return key;
    }
}
