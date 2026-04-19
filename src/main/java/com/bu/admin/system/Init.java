package com.bu.admin.system;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by ghostWu on 2017/4/25.
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface Init {
}
