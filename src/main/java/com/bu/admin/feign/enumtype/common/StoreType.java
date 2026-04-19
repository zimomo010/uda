package com.bu.admin.feign.enumtype.common;

/**
 * Created by GhostWu on 2015/3/16.
 * 订单状态类型
 */
public enum StoreType {

    COS("腾讯COS"),
    OSS("阿里OSS"),
    LOCAL("本地"),
    ;

    StoreType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }

}
