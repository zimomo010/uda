package com.bu.admin.feign.enumtype.user;

/**
 * Created by  on 2015/3/16.
 * saas type
 */
public enum AreaCodes {

    CN("中国大陆"),
    HKG("香港"),
    MO("澳门"),
    TW("台湾"),
    SG("新加坡"),
    US("美国")
    ;

    AreaCodes(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
