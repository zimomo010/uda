package com.bu.admin.feign.enumtype.quartz;

/**
 * Created by GhostWu on 2015/3/16.
 * 订单状态类型
 */
public enum BatchDealType {

    BLOOM_BERG("BLOOM BERG"),

    ;

    BatchDealType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }

}
