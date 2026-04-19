package com.bu.admin.feign.enumtype.common;
/**
 * Created by Dr_Yuan on 2015/3/16.
 * 订单状态类型
 */
public enum AccountTypeEnum {
    //trade type
    FINANCE_ACC("金融账户"),
    BANK_ACC("银行账户"),
    ALIPAY_ACC("支付宝账户"),
    WECHAT_ACC("微信账户");

    AccountTypeEnum(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}