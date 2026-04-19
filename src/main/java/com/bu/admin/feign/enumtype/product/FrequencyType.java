package com.bu.admin.feign.enumtype.product;

/**
 * Created by ghostWu on 2017/5/9.
 */
public enum FrequencyType {

    DAILY("日"),
    WEEKLY("周"),
    MONTHLY("月"),
    QUARTERLY("季度"),
    SEMI_ANNUALLY("半年"),
    ANNUALLY("年");

    FrequencyType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
