package com.bu.admin.feign.enumtype.common;

/**
 * Created by ghostWu on 2017/5/9.
 */
public enum DataType {

    STRING("字符"),
    INTEGER("数字"),
    DATE("日期"),
    BOOLEAN("布尔");

    DataType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
