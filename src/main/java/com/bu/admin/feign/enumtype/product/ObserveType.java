package com.bu.admin.feign.enumtype.product;

/**
 * Created by ghostWu on 2017/5/9.
 */
public enum ObserveType {

    CONTINUOUS("持续观察"),
    MATURITY_ONLY("成熟后观察"),
    ;

    ObserveType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
