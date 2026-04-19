package com.bu.admin.feign.enumtype.product;

/**
 * Created by  on 2015/3/16.
 * saas type
 */
public enum PriceType {

    BUY("买入"),
    SELL("卖出"),
    ;

    PriceType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
