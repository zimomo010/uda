package com.bu.admin.feign.enumtype.product;

/**
 * Created by  on 2015/3/16.
 * saas type
 */
public enum IdentifyType {

    IDENTIFY_CARD_HK("香港身份证"),
    IDENTIFY_CARD_CH("内地身份证"),
    PASSPORT_CARD_HK("香港护照"),
    PASSPORT_CARD_CH("内地护照"),
    OTHER("其他");

    IdentifyType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
