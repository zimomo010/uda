package com.bu.admin.feign.enumtype.product;

/**
 * Created by ghostWu on 2017/5/9.
 */
public enum SettlementType {

    FOP("FOP"),
    DVP("DVP"),
    MIX("MIX"),
    UNKNOWN("UNKNOWN");

    SettlementType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
