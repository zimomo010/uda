package com.bu.admin.feign.enumtype.transaction;

/**
 * Created by  on 2015/3/16.
 * saas type
 */
public enum TransStage {

    PRE_ISSUE("发行前"),
    ISSUE("发行中"),
    REDEEMED("已赎回"),
    MATURED("已到期");

    TransStage(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
