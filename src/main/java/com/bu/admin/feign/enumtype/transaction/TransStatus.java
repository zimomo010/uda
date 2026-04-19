package com.bu.admin.feign.enumtype.transaction;

/**
 * Created by  on 2015/3/16.
 * saas type
 */
public enum TransStatus {

    CREATE("订单创建"),
    FUND_DELIVERING("资金交割中"),
    SECURITY_DELIVERING("证券交割中"),
    FINISH("订单完成"),
    CANCEL("订单取消"),
    CLOSE("订单关闭"),;

    TransStatus(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
