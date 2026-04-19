package com.bu.admin.feign.enumtype.common;

import lombok.Getter;


@Getter
public enum FileType {

    PRODUCT("产品"),
    TRANSACTION("交易"),
    COMPANY("公司"),
    CUSTOMER("客户"),
    CURVE("CURVE"),
    FLT_CPN("Flt Cpn Data"),
    REPORT("report"),
    RATE("RATE"),
    FX("FX"),
    BOND("BOND"),
    COUPON("COUPON"),
    FUND("FUND"),
    OPTION("option market data"),
    EQU_BASIC("Equ Basic"),
    EQU_PRICE("Equ Price"),
    EQU_EVENT("Equ Event"),
    CLN("Cln"),
    CDS("Cds"),
    SN("SN"),;

    FileType(String desc) {
        this.desc = desc;
    }

    private final String desc;

}
