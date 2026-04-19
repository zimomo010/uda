package com.bu.admin.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * restful 注解处理
 * Created with IntelliJ IDEA.
 * User: ghost
 * Date: 14-2-19
 * Time: 上午10:59
 * To change this template use File | Settings | File Templates.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface WsOperation {
    String value() default ""; // 操作名称

    boolean tokenNeeded() default true; // 是否需要校验用户token

    boolean serverTokenNeeded() default false;//是否需要服务端token

    boolean permissionNeeded() default true; // 是否需要校验用户权限

    String[] requiredParams() default {}; // 必传字段

    String[] ignoreLogFields() default {}; // 日志记录忽略字段

    String[] ignoreLogOutputFields() default {};// 日志响应记录忽略字段
}
