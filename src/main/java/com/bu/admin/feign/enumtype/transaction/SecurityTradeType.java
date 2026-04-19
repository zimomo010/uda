package com.bu.admin.feign.enumtype.transaction;

/**
 * Created by ghostWu on 2017/5/9.
 */
public enum SecurityTradeType {

    BUY("买入"),
    SELL("卖出"),
    ;

    SecurityTradeType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
