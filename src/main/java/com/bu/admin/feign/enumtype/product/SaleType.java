package com.bu.admin.feign.enumtype.product;

/**
 * Created by  on 2015/3/16.
 * saas type
 */
public enum SaleType {

    BROKER("经纪型"),
    DEALER("做市型"),
    ;

    SaleType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
