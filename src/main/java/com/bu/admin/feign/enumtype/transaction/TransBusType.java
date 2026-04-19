package com.bu.admin.feign.enumtype.transaction;

/**
 * Created by  on 2015/3/16.
 * saas type
 */
public enum TransBusType {

    PRE_ISSUED("预发行"),
    ISSUED("已发行"),
    POST_ISSUE_UPDATED("发行后更新"),
    UPSIZED("扩大规模"),
    REDEEMED("赎回"),
    MATURED("到期");

    TransBusType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
