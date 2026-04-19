package com.bu.admin.feign.enumtype.transaction;

/**
 * Created by  on 2015/3/16.
 * saas type
 */
public enum TransValuationType {

    HTM("HTM"),
    MTM("MTM"),
    CLN_MTM("CLN_MTM"),
    TRS_MTM("TRS_MTM"),
    ;

    TransValuationType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
