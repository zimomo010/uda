package com.bu.admin.feign.enumtype.transaction;

/**
 * Created by ghostWu on 2017/5/9.
 */
public enum RelativeInternalType {

    ORIGIN("Origin"),
    HEDGE("Hedge"),
    ;

    RelativeInternalType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
