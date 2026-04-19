package com.bu.admin.feign.enumtype.risk;

/**
 * Created by  on 2015/3/16.
 * saas type
 */
public enum TransRiskType {

    FX("FX"),
    MTM("MTM"),
    ;

    TransRiskType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
