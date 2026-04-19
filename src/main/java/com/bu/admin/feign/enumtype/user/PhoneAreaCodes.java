package com.bu.admin.feign.enumtype.user;

/**
 * Created by  on 2015/3/16.
 * saas type
 */
public enum PhoneAreaCodes {

    CN("+86"),
    HKG("+852"),
    MO("+853"),
    TW("+886"),
    SG("+65"),;

    PhoneAreaCodes(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
