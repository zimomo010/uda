package com.bu.admin.feign.enumtype.funding;

public enum FundingStatus {
    OUTSTANDING ("Outstanding"),
    MATURED("Matured"),
    ;

    FundingStatus(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
