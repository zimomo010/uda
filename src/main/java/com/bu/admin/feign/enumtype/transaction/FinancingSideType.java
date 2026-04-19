package com.bu.admin.feign.enumtype.transaction;

public enum FinancingSideType {
    PAY("Pay"),
    RECEIVE("Receive");

    FinancingSideType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
