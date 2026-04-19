package com.bu.admin.feign.enumtype.product;

/**
 * Created by ghostWu on 2017/5/9.
 */
public enum CurrencyType {

    HKD("港币"),
    CNY("人民币"),
    CNH("离岸人民币"),
    USD("美元"),
    EUR("欧元"),
    JPY("日元"),
    AUD("澳元"),
    GBP("英镑"),
    THB("泰铢"),
    NZD("纽币"),
    CAD("加元"),

    MULTIPLE("MULTIPLE"),
    CNYM("CNYMUSD"),
    ALL("ALL"),
    UNKNOWN("unknown");

    CurrencyType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
