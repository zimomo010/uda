package com.bu.admin.feign.enumtype.product;

/**
 * Created by ghostWu on 2017/5/9.
 */
public enum BarrierLevelType {

    UP_BARRIER("上线障碍"),
    LOW_BARRIER("下线障碍"),
    ;

    BarrierLevelType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
