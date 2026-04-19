package com.bu.admin.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface WsOperation {
    String value() default "";

    boolean tokenNeeded() default true;

    boolean serverTokenNeeded() default false;

    boolean permissionNeeded() default true;

    String[] requiredParams() default {};

    String[] ignoreLogFields() default {};

    String[] ignoreLogOutputFields() default {};
}
