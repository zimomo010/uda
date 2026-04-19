package com.bu.admin.feign.enumtype.transaction;

public enum ValuationAdjustType {

    AMOUNT("amount"),
    RATE("rate"),
    ONE_TIME("one-time");
    ;

    ValuationAdjustType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
