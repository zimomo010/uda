package com.bu.admin.feign.enumtype.report;

/**
 * Created by GhostWu on 2015/3/16.
 * 订单状态类型
 */
public enum RequestUserType {

    CUSTOMER("Customer"),
    EMPLOYEE("Employee"),
    ;

    RequestUserType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }

}
