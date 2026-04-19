package com.bu.admin.feign.enumtype.pnl;

/**
 * Created by  on 2015/3/16.
 * saas type
 */
public enum PnlType {

    SUMMARY("Summary"),
    BOND("Bond"),
    FX("FX"),
    FINANCING("Financing"),
    FEE("Fee"),
    SUMMARY_SALF("SUMMARY_SALF")
    ;

    PnlType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
