package com.bu.admin.feign.enumtype.common;

import lombok.Getter;

/**
 * Created by  on 2015/3/16.
 * saas type
 */
@Getter
public enum TaskStatus {

    UN_FINISH("未完成"),
    FINISH("已完成"),
    EXPIRED("已过期"),;

    TaskStatus(String desc) {
        this.desc = desc;
    }

    private final String desc;

}
