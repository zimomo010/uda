package com.bu.admin.feign.enumtype.funding;

public enum FundingType {

    SMCN("Smcn"),
    REPO("Repo"),
    TRS("TRS"),
    ;


    FundingType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
