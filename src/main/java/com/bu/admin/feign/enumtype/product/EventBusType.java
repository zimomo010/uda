package com.bu.admin.feign.enumtype.product;

/**
 * Created by  on 2015/3/16.
 * saas type
 */
public enum EventBusType {

    CONTENT("内容"),
    FINANCIAL_DATA("金融数据"),
    PRODUCT("产品"),;

    EventBusType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
