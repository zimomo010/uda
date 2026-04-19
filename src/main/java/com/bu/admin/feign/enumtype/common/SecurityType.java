package com.bu.admin.feign.enumtype.common;

/**
 * Created by  on 2015/3/16.
 * saas type
 */
public enum SecurityType {

    STOCK("Stock"),
    SN("SN"),//StructureNote
    BOND("Bond"),
    FUND("Fund"),
    RATE("Rate"),
    CLN("Cln"),
    CDS("Cds"),
    CURVE("CURVE"),
    TRS("Trs"),
    /*  option */
    OPTION("Option"),
    OP_BOND("Option Bond"),
    OP_FX("Option Fx"),
    COUPON("Coupon"),
    EQU("Equity")
    ;

    SecurityType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
