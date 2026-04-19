package com.bu.admin.feign.enumtype.product;

/**
 * Created by ghostWu on 2017/5/9.
 */
public enum ProductEventType {

    EARLY_REDEMPTION ("提前赎回"),
    COUPON_PAY("票据派息"),
    BARRIER_TRIGGERED("障碍触发"),
    FINANCING("融资"),
    INITIAL_DEPOSIT("初期存款"),
    WITHDRAW_BEFORE_ISSUANCE("发行前提现"),
    FX_COLLATERAL("定期存款/外汇担保货币"),
    PAYMENT("支付"),
    IRS("利率互换"),
    HEDGE_FINANCING("Hedge融资"),
    HOUSE_REPO("house repo"),
    CLIENT_REPO("client repo"),
    // ftp
    STRUCTURED_NOTE("structured note"),
    QFII("QFII"),
    EQD("EQD"),
    D1("D1"),
    PROP("prop"),
    OTHERS("others"),
    ;

    ProductEventType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
