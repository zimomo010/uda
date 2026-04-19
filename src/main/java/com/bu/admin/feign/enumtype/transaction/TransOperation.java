package com.bu.admin.feign.enumtype.transaction;

/**
 * Created by  on 2015/3/16.
 * saas type
 */
public enum TransOperation {

    CREATE_TRANSACTION("订单创建"),
    CONFIRM_TRANSACTION("订单确认"),
    FUND_DELIVERY_CONFIRM("资金交割完成"),
    SECURITY_DELIVERY_CONFIRM("证券交割完成"),
    CLOSE_TRANSACTION("订单关闭"),
    CANCEL_TRANSACTION("订单取消"),
    NOTE_BONDING_INPUT("票据数据导入"),
    FX_BONDING_INPUT("换汇数据导入"),
    BOND_BONDING_INPUT("资产数据导入"),
    TOP_UP("补仓"),
    ;

    TransOperation(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
