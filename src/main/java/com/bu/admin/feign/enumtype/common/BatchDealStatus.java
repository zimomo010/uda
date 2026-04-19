package com.bu.admin.feign.enumtype.common;

/**
 * Created by GhostWu on 2015/3/16.
 * 订单状态类型
 */
public enum BatchDealStatus {

    WAIT_DEAL("待处理"),
    DEALING("处理中"),
    FINISH("已完成"),
    FAILURE("处理失败"),
    ;

    BatchDealStatus(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }

}
